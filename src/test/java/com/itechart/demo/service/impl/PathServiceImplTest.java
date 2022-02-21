package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.converter.CitiesAndRoutesToGraphConverter;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.EmptyInputException;
import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Graph;
import com.itechart.demo.service.model.Path;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PathServiceImplTest {
	@Mock
	private PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService;
	@Mock
	private RouteService routeService;
	@Mock
	private CityService cityService;
	@Mock
	private CitiesAndRoutesToGraphConverter citiesAndRoutesToGraphConverter;

	private PathServiceImpl underTest;

	@BeforeEach
	void setUp() {
		underTest = new PathServiceImpl(pathDepthFirstSearchCalculatorService,
				citiesAndRoutesToGraphConverter,
				routeService,
				cityService);

	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void canGetMultiPaths() throws GraphNullException, PathNotFoundException, RouteNotFoundException,
			CityNotFoundException, EmptyInputException {
		String nodeA = "A";
		String nodeB = "B";
		String nodeC = "C";

		Long cityAid = 1L;
		Long cityBid = 2L;
		Long cityCid = 3L;

		City cityA = new City();
		cityA.setName(nodeA);
		cityA.setId(cityAid);
		City cityB = new City();
		cityB.setName(nodeB);
		cityB.setId(cityBid);
		City cityC = new City();
		cityC.setName(nodeC);
		cityC.setId(cityCid);

		List<City> cities = new ArrayList<>(Arrays.asList(cityA, cityB, cityC));

		Route routeAC = new Route();
		routeAC.setFirstCity(cityA);
		routeAC.setSecondCity(cityC);
		routeAC.setDistance(10F);

		Route routeAB = new Route();
		routeAB.setFirstCity(cityA);
		routeAB.setSecondCity(cityB);
		routeAB.setDistance(10F);

		Route routeBC = new Route();
		routeBC.setFirstCity(cityB);
		routeBC.setSecondCity(cityC);
		routeBC.setDistance(10F);

		List<Route> routes = new ArrayList<>(Arrays.asList(routeAB, routeAC, routeBC));

		Mockito.doReturn(cities).when(cityService).findAll();
		Mockito.doReturn(routes).when(routeService).findAll();

		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>();
		adjacentNodesToA.add(nodeB);
		adjacentNodesToA.add(nodeC);
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>();
		adjacentNodesToB.add(nodeA);
		adjacentNodesToB.add(nodeC);
		LinkedHashSet<String> adjacentNodesToC = new LinkedHashSet<>();
		adjacentNodesToC.add(nodeB);
		adjacentNodesToC.add(nodeA);

		Graph graph = new Graph();
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(nodeA, adjacentNodesToA);
		graphMap.put(nodeB, adjacentNodesToB);
		graphMap.put(nodeC, adjacentNodesToC);
		graph.setGraph(graphMap);
		Mockito.doReturn(graph).when(citiesAndRoutesToGraphConverter)
				.convert(ArgumentMatchers.any(), ArgumentMatchers.any());

		Set<LinkedList<String>> stringPaths = new HashSet<>();
		LinkedList<String> firstStringPath = new LinkedList<>();
		firstStringPath.add(nodeA);
		firstStringPath.add(nodeB);
		firstStringPath.add(nodeC);
		LinkedList<String> secondStringPath = new LinkedList<>();
		secondStringPath.add(nodeA);
		secondStringPath.add(nodeC);
		stringPaths.add(firstStringPath);
		stringPaths.add(secondStringPath);

		Mockito.doReturn(stringPaths).when(pathDepthFirstSearchCalculatorService)
				.calculatePaths(graph, cityA.getName(), cityC.getName());

		Path firstPath = new Path();
		Set<Route> firstRoutesPath = new HashSet<>();
		firstRoutesPath.add(routeAB);
		firstRoutesPath.add(routeBC);
		firstPath.setRoutesToEndPoint(firstRoutesPath);
		firstPath.setTotalDistance(20F);

		Path secondPath = new Path();
		Set<Route> secondRoutesPath = new HashSet<>();
		secondRoutesPath.add(routeAC);
		secondPath.setRoutesToEndPoint(secondRoutesPath);
		secondPath.setTotalDistance(10F);

		Set<Path> expected = new HashSet<>();
		expected.add(secondPath);
		expected.add(firstPath);

		Answer<Route> answerGetRouteBetweenTwoCities = invocationOnMock -> {
			City firstCity = invocationOnMock.getArgument(0, City.class);
			City secondCity = invocationOnMock.getArgument(1, City.class);
			return routes.stream().filter(route -> route.getFirstCity().equals(firstCity)
					&& route.getSecondCity().equals(secondCity)).findAny().get();
		};

		Answer<City> answerGetCityByName = invocationOnMock -> {
			String cityName = invocationOnMock.getArgument(0, String.class);
			return cities.stream().filter(city -> city.getName().equals(cityName)).findAny().get();
		};

		Answer<City> answerGetCityById = invocationOnMock -> {
			Long arg = invocationOnMock.getArgument(0, Long.class);
			return cities.stream().filter(city -> city.getId().equals(arg)).findAny().get();
		};
		Mockito.when(cityService.findById(ArgumentMatchers.any())).thenAnswer(answerGetCityById);
		Mockito.when(cityService.findByName(ArgumentMatchers.anyString())).thenAnswer(answerGetCityByName);
		Mockito.when(routeService.findRouteBetweenCities(ArgumentMatchers.any(City.class),
						ArgumentMatchers.any(City.class)))
				.thenAnswer(answerGetRouteBetweenTwoCities);

		Set<Path> result = underTest.getPaths(cityAid, cityCid);

		Mockito.verify(cityService).findById(cityA.getId());
		Mockito.verify(cityService).findById(cityC.getId());
		Mockito.verify(pathDepthFirstSearchCalculatorService).calculatePaths(graph, cityA.getName(), cityC.getName());

		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	void getPathsThrowsPathNotFoundException() throws GraphNullException, PathNotFoundException, CityNotFoundException, EmptyInputException {
		City firstCity = new City();
		Long firstCityId = 1L;
		String firstCityName = "London";
		firstCity.setId(firstCityId);
		firstCity.setName(firstCityName);

		City secondCity = new City();
		Long secondCityId = 2L;
		String secondCityName = "New York";
		secondCity.setId(secondCityId);
		secondCity.setName(secondCityName);

		List<City> cities = new ArrayList<>(Arrays.asList(firstCity, secondCity));
		List<Route> routes = new ArrayList<>();

		Mockito.doReturn(cities).when(cityService).findAll();
		Mockito.doReturn(routes).when(routeService).findAll();

		Set<LinkedList<String>> stringPaths = new HashSet<>();

		Answer<City> answerGetCityById = invocationOnMock -> {
			Long arg = invocationOnMock.getArgument(0, Long.class);
			return cities.stream().filter(city -> city.getId().equals(arg)).findAny().get();
		};

		Mockito.when(cityService.findById(ArgumentMatchers.any())).thenAnswer(answerGetCityById);

		Mockito.doReturn(stringPaths).when(pathDepthFirstSearchCalculatorService)
				.calculatePaths(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());

		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.getPaths(firstCity.getId(), secondCity.getId()))
				.isInstanceOf(PathNotFoundException.class);
	}
}
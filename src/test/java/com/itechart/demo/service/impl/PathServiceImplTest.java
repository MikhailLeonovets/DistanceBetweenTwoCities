package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.converter.CitiesAndRoutesToGraphConverter;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.DataInputException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Graph;
import com.itechart.demo.service.model.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private static final String CITY_NAME_A = "A";
	private static final String CITY_NAME_B = "B";
	private static final String CITY_NAME_C = "C";
	private static final Long ID_1 = 1L;
	private static final Long ID_2 = 2L;
	private static final Long ID_3 = 3L;
	private static final Float DISTANCE = 10F;

	private PathServiceImpl pathService;

	@BeforeEach
	void setUp() {
		pathService = new PathServiceImpl(pathDepthFirstSearchCalculatorService,
				citiesAndRoutesToGraphConverter,
				routeService,
				cityService);
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testGetPathsReturnsTwoPaths() throws PathNotFoundException, RouteNotFoundException,
			CityNotFoundException, DataInputException {
		// Given
		City cityA = new City(ID_1, CITY_NAME_A);
		City cityB = new City(ID_2, CITY_NAME_B);
		City cityC = new City(ID_3, CITY_NAME_C);
		List<City> cities = new ArrayList<>(Arrays.asList(cityA, cityB, cityC));
		List<Route> routes = getRoutesFromThreeCities(cityA, cityB, cityC);
		Mockito.doReturn(cities).when(cityService).findAll();
		Mockito.doReturn(routes).when(routeService).findAll();
		Graph graph = getGraphWithThreeCities();
		Mockito.doReturn(graph).when(citiesAndRoutesToGraphConverter)
				.convert(ArgumentMatchers.any(), ArgumentMatchers.any());
		Set<LinkedList<String>> stringPaths = getStringPathsForTestGetPathsReturnsTwoPaths();
		Mockito.doReturn(stringPaths).when(pathDepthFirstSearchCalculatorService)
				.calculatePaths(graph, cityA.getName(), cityC.getName());

		Mockito.when(cityService.findById(ArgumentMatchers.any()))
				.thenAnswer(invocationOnMock -> getCityById(cities, invocationOnMock));
		Mockito.when(cityService.findByName(ArgumentMatchers.anyString()))
				.thenAnswer(invocationOnMock -> getCityByName(cities, invocationOnMock));
		Mockito.when(routeService.findRouteBetweenCities(ArgumentMatchers.any(City.class),
						ArgumentMatchers.any(City.class)))
				.thenAnswer(invocationOnMock -> getRouteBetweenTwoCities(routes, invocationOnMock));
		Set<Path> expectedResult = getPathsFromRoutesForTestGetPathsReturnsTwoPaths(routes);

		// When
		Set<Path> actualResult = pathService.getPaths(ID_1, ID_3);

		// Then
		Mockito.verify(cityService).findById(cityA.getId());
		Mockito.verify(cityService).findById(cityC.getId());
		Mockito.verify(pathDepthFirstSearchCalculatorService).calculatePaths(graph, cityA.getName(), cityC.getName());
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void getPathsThrowsPathNotFoundException() throws PathNotFoundException, CityNotFoundException,
			DataInputException {
		// Given
		City firstCity = new City(ID_1, CITY_NAME_A);
		City secondCity = new City(ID_2, CITY_NAME_B);
		List<City> cities = new ArrayList<>(Arrays.asList(firstCity, secondCity));
		List<Route> routes = new ArrayList<>();

		Mockito.doReturn(cities).when(cityService).findAll();
		Mockito.doReturn(routes).when(routeService).findAll();
		Set<LinkedList<String>> stringPaths = new HashSet<>();

		Mockito.when(cityService.findById(ArgumentMatchers.any()))
				.thenAnswer(invocationOnMock -> getCityById(cities, invocationOnMock));
		Mockito.doReturn(stringPaths).when(pathDepthFirstSearchCalculatorService)
				.calculatePaths(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());

		// When
		// Then
		Assertions.assertThrows(PathNotFoundException.class,
				() -> pathService.getPaths(firstCity.getId(), secondCity.getId()));
	}

	/**
	 * @return List of Routes. Routes are generated for Graph with these Cities where might be two different paths
	 * from City A to City C.
	 */
	private List<Route> getRoutesFromThreeCities(City cityA, City cityB, City cityC) {
		Route routeAC = new Route(cityA, cityC, DISTANCE);
		Route routeAB = new Route(cityA, cityB, DISTANCE);
		Route routeBC = new Route(cityB, cityC, DISTANCE);
		return new ArrayList<>(Arrays.asList(routeAB, routeAC, routeBC));
	}

	/**
	 * @return graph where city A, city B and city C. City A is connected to City B and City C.
	 * City B is connected to City A and City C. City C is connected to City A and City B.
	 */
	private Graph getGraphWithThreeCities() {
		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>(Arrays.asList(CITY_NAME_B, CITY_NAME_C));
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>(Arrays.asList(CITY_NAME_A, CITY_NAME_C));
		LinkedHashSet<String> adjacentNodesToC = new LinkedHashSet<>(Arrays.asList(CITY_NAME_B, CITY_NAME_A));

		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(CITY_NAME_A, adjacentNodesToA);
		graphMap.put(CITY_NAME_B, adjacentNodesToB);
		graphMap.put(CITY_NAME_C, adjacentNodesToC);
		return new Graph(graphMap);
	}

	/**
	 * @return Set<LinkedList < String>> string paths for expected result from test testGetPathsReturnsTwoPaths().
	 */
	private Set<LinkedList<String>> getStringPathsForTestGetPathsReturnsTwoPaths() {
		LinkedList<String> firstStringPath = new LinkedList<>(Arrays.asList(CITY_NAME_A, CITY_NAME_B, CITY_NAME_C));
		LinkedList<String> secondStringPath = new LinkedList<>(Arrays.asList(CITY_NAME_A, CITY_NAME_C));
		return new HashSet<>(Arrays.asList(firstStringPath, secondStringPath));
	}

	/**
	 * @param routes are to be added to Paths.
	 * @return Set<Path> for expected result of paths for testGetPathsReturnsTwoPaths().
	 */
	private Set<Path> getPathsFromRoutesForTestGetPathsReturnsTwoPaths(List<Route> routes) {
		Path firstPath = new Path();
		firstPath.setRoutesToEndPoint(new HashSet<>(Arrays.asList(routes.get(0), routes.get(2))));
		Float totalDistancePathOne = 20F;
		firstPath.setTotalDistance(totalDistancePathOne);

		Path secondPath = new Path();
		secondPath.setRoutesToEndPoint(new HashSet<>(Arrays.asList(routes.get(1))));
		Float totalDistancePathTwo = 10F;
		secondPath.setTotalDistance(totalDistancePathTwo);
		return new HashSet<>(Arrays.asList(firstPath, secondPath));
	}

	private City getCityById(List<City> cities, InvocationOnMock invocationOnMock) {
		int idArgNumber = 0;
		Long arg = invocationOnMock.getArgument(idArgNumber, Long.class);
		return cities.stream().filter(city -> city.getId().equals(arg)).findAny().get();
	}

	private Route getRouteBetweenTwoCities(List<Route> routes, InvocationOnMock invocationOnMock) {
		int firstCityArgNumber = 0;
		int secondCityArgNumber = 1;
		City firstCity = invocationOnMock.getArgument(firstCityArgNumber, City.class);
		City secondCity = invocationOnMock.getArgument(secondCityArgNumber, City.class);
		return routes.stream().filter(route -> route.getFirstCity().equals(firstCity)
				&& route.getSecondCity().equals(secondCity)).findAny().get();
	}

	private City getCityByName(List<City> cities, InvocationOnMock invocationOnMock) {
		int cityNameArgNumber = 0;
		String cityName = invocationOnMock.getArgument(cityNameArgNumber, String.class);
		return cities.stream().filter(city -> city.getName().equals(cityName)).findAny().get();
	}
}
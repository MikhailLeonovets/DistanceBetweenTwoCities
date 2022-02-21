package com.itechart.demo.service.impl.database_service.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.spring_data.RouteRepository;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RouteSpringDataServiceTest {
	@Mock
	private RouteRepository repository;
	private RouteSpringDataService routeSpringDataService;

	private static final Long ID_1 = 1L;

	@BeforeEach
	void setUp() {
		routeSpringDataService = new RouteSpringDataService(repository);
	}

	@Test
	void testSaveInDataBase() {
		// Given
		Route route = new Route();

		// When
		routeSpringDataService.saveInDataBase(route);

		// Then
		Mockito.verify(repository).save(route);
	}

	@Test
	void testFindAll() {
		// When
		routeSpringDataService.findAll();

		// Then
		Mockito.verify(repository).findAll();
	}

	@Test
	void testFindById() throws RouteNotFoundException {
		// Given
		Route route = new Route();
		route.setId(ID_1);
		Mockito.doReturn(Optional.of(route)).when(repository).findById(ID_1);

		// When
		Route returnedRoute = routeSpringDataService.findById(ID_1);

		// Then
		Mockito.verify(repository).findById(ArgumentMatchers.eq(ID_1));
		Assertions.assertNotNull(returnedRoute);
	}

	@Test
	void testFindByIdThrowsRouteNotFoundException() {
		// Given
		Mockito.doReturn(Optional.empty()).when(repository).findById(ArgumentMatchers.any());

		// When
		// Then
		Assertions.assertThrows(RouteNotFoundException.class,
				() -> routeSpringDataService.findById(ArgumentMatchers.any()));
	}

	@Test
	void testFindRoutesByFirstCity() throws RouteNotFoundException {
		// Given
		Route route = new Route();
		City city = new City();
		List<Route> routeList = new ArrayList<>();
		routeList.add(route);
		Mockito.doReturn(routeList).when(repository).findRoutesByFirstCity(city);

		// When
		routeSpringDataService.findRoutesByFirstCity(city);

		// Then
		Mockito.verify(repository).findRoutesByFirstCity(city);
	}

	@Test
	void testFindRoutesByFirstCityThrowsRouteNotFoundException() {
		// Given
		City city = new City();
		List<Route> routes = new ArrayList<>();
		Mockito.doReturn(routes).when(repository).findRoutesByFirstCity(city);

		// When
		// Then
		Assertions.assertThrows(RouteNotFoundException.class, () -> routeSpringDataService.findRoutesByFirstCity(city));
	}

	@Test
	void testUpdate() throws RouteNotFoundException {
		// Given
		Route route = new Route();
		Mockito.doReturn(Optional.of(route)).when(repository).findById(route.getId());

		// When
		routeSpringDataService.update(route);

		// Then
		Mockito.verify(repository).save(route);
	}

	@Test
	void testUpdateThrowsRouteNotFoundException() {
		// Given
		Route route = new Route();
		Mockito.doReturn(Optional.empty()).when(repository).findById(route.getId());

		// When
		// Then
		Assertions.assertThrows(RouteNotFoundException.class, () -> routeSpringDataService.update(route));
		Mockito.verify(repository, Mockito.never()).save(route);
	}

	@Test
	void testFindRouteBetweenCities() throws RouteNotFoundException {
		// Given
		City firstCity = new City();
		City secondCity = new City();
		Route route = new Route();
		Mockito.doReturn(Optional.of(route)).when(repository)
				.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		// When
		routeSpringDataService.findRouteBetweenCities(firstCity, secondCity);

		// Then
		Mockito.verify(repository).findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
	}

	@Test
	void testFindRouteBetweenCitiesThrowsRouteNotFoundException() {
		// Given
		City firstCity = new City();
		City secondCity = new City();
		Mockito.doReturn(Optional.empty()).when(repository)
				.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		// When
		// Then
		Assertions.assertThrows(RouteNotFoundException.class,
				() -> routeSpringDataService.findRouteBetweenCities(firstCity, secondCity));
		Mockito.verify(repository).findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
	}

	@Test
	void testDeleteById() {
		// When
		routeSpringDataService.deleteById(ID_1);

		// Then
		Mockito.verify(repository).deleteById(ID_1);
	}

	@Test
	void testDelete() {
		// Given
		Route route = new Route();

		// When
		routeSpringDataService.delete(route);

		// Then
		Mockito.verify(repository).delete(route);
	}
}
package com.itechart.demo.service.impl.database_service.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.spring_data.RouteRepository;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RouteSpringDataServiceTest {
	@Mock
	private RouteRepository repository;
	private RouteSpringDataService underTest;

	@BeforeEach
	void setUp() {
		underTest = new RouteSpringDataService(repository);
	}

	@Test
	void canSaveInDataBase() {
		Route route = new Route();
		underTest.saveInDataBase(route);
		Mockito.verify(repository).save(route);
	}

	@Test
	void canFindAll() {
		underTest.findAll();
		Mockito.verify(repository).findAll();
	}

	@Test
	void canFindById() throws RouteNotFoundException {
		Long id = 1L;
		Route route = new Route();
		Mockito.doReturn(Optional.of(route)).when(repository).findById(id);
		Route returnedRoute = underTest.findById(id);
		Mockito.verify(repository).findById(ArgumentMatchers.eq(id));
		Assertions.assertNotNull(returnedRoute);
	}

	@Test
	void findByIdThrowsRouteNotFoundException() {
		Mockito.doReturn(Optional.empty()).when(repository).findById(ArgumentMatchers.any());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findById(ArgumentMatchers.any()))
				.isInstanceOf(RouteNotFoundException.class);
	}

	@Test
	void canFindRoutesByFirstCity() throws RouteNotFoundException {
		Route route = new Route();
		City city = new City();
		List<Route> routeList = new ArrayList<>();
		routeList.add(route);
		Mockito.doReturn(routeList).when(repository).findRoutesByFirstCity(city);
		underTest.findRoutesByFirstCity(city);
		Mockito.verify(repository).findRoutesByFirstCity(city);
	}

	@Test
	void findRoutesByFirstCityThrowsRouteNotFoundException() {
		City city = new City();
		List<Route> routes = new ArrayList<>();
		Mockito.doReturn(routes).when(repository).findRoutesByFirstCity(city);
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findRoutesByFirstCity(city))
				.isInstanceOf(RouteNotFoundException.class);
	}

	@Test
	void canUpdate() throws RouteNotFoundException {
		Route route = new Route();
		Mockito.doReturn(Optional.of(route)).when(repository).findById(route.getId());
		underTest.update(route);
		Mockito.verify(repository).save(route);
	}

	@Test
	void updateThrowsRouteNotFoundException() {
		Route route = new Route();
		Mockito.doReturn(Optional.empty()).when(repository).findById(route.getId());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.update(route))
				.isInstanceOf(RouteNotFoundException.class);
		Mockito.verify(repository, Mockito.never()).save(route);
	}

	@Test
	void canFindRouteBetweenCities() throws RouteNotFoundException {
		City firstCity = new City();
		City secondCity = new City();
		Route route = new Route();
		Mockito.doReturn(Optional.of(route)).when(repository)
				.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
		underTest.findRouteBetweenCities(firstCity, secondCity);
		Mockito.verify(repository).findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
	}

	@Test
	void findRouteBetweenCities() {
		City firstCity = new City();
		City secondCity = new City();
		Mockito.doReturn(Optional.empty()).when(repository)
				.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findRouteBetweenCities(firstCity, secondCity))
				.isInstanceOf(RouteNotFoundException.class);
		Mockito.verify(repository).findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
	}

	@Test
	void deleteById() {
		Long id = 1L;
		underTest.deleteById(id);
		Mockito.verify(repository).deleteById(id);
	}

	@Test
	void delete() {
		Route route = new Route();
		underTest.delete(route);
		Mockito.verify(repository).delete(route);
	}
}
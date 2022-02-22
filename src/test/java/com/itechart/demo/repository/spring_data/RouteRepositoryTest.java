package com.itechart.demo.repository.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class RouteRepositoryTest {
	@Autowired
	private RouteRepository routeRepository;
	@Autowired
	private CityRepository cityRepository;

	private static final String CITY_NAME_NEW_YORK = "New York";
	private static final String CITY_NAME_LA = "LA";

	private static final Float DISTANCE = 25F;

	@AfterEach
	void tearDown() {
		routeRepository.deleteAll();
		cityRepository.deleteAll();
	}

	@Test
	void testFindRoutesByFirstCityReturnsNothing() {
		// Given
		City city = new City(CITY_NAME_NEW_YORK);
		saveCitiesToDatabase(Arrays.asList(city));

		// When
		List<Route> routeList = routeRepository.findRoutesByFirstCity(city);

		// Then
		assertThat(routeList).asList().isEmpty();
	}

	@Test
	void testFindRoutesByFirstCityReturnsOneRoute() {
		// Given
		City firstCity = new City(CITY_NAME_NEW_YORK);
		City secondCity = new City(CITY_NAME_LA);
		saveCitiesToDatabase(Arrays.asList(firstCity, secondCity));
		routeRepository.save(new Route(firstCity, secondCity, DISTANCE));
		int expectedResultListSize = 1;

		// When
		List<Route> routeList = routeRepository.findRoutesByFirstCity(firstCity);

		// Then
		assertThat(routeList).asList().size().isEqualTo(expectedResultListSize);
	}

	@Test
	void testFindRouteBetweenTwoCitiesReturnsNothing() {
		// Given
		City firstCity = new City(CITY_NAME_NEW_YORK);
		City secondCity = new City(CITY_NAME_LA);
		saveCitiesToDatabase(Arrays.asList(firstCity, secondCity));

		// When
		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		// Then
		assertThat(optionalRoute.isPresent()).isFalse();
	}

	@Test
	void testFindRouteBetweenTwoCities() {
		// Given
		City firstCity = new City(CITY_NAME_NEW_YORK);
		City secondCity = new City(CITY_NAME_LA);
		saveCitiesToDatabase(Arrays.asList(firstCity, secondCity));
		routeRepository.save(new Route(firstCity, secondCity, DISTANCE));

		// When
		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		// Then
		assertThat(optionalRoute.isPresent()).isTrue();
	}

	private void saveCitiesToDatabase(List<City> cities) {
		cities.forEach(cityRepository::save);
	}
}

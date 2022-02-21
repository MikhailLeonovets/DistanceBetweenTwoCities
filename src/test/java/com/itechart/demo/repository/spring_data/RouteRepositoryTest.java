package com.itechart.demo.repository.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

	private static final Float DISTANCE = 23F;

	@AfterEach
	void tearDown() {
		routeRepository.deleteAll();
		cityRepository.deleteAll();
	}

	@Test
	void findNoRoutesByFirstCity() {
		// Given
		City city = new City();
		city.setName(CITY_NAME_NEW_YORK);
		city = cityRepository.save(city);

		// When
		List<Route> routeList = routeRepository.findRoutesByFirstCity(city);

		// Then
		assertThat(routeList).asList().isEmpty();
	}

	@Test
	void findOneRouteByFirstCity() {
		// Given
		City firstCity = new City();
		firstCity.setName(CITY_NAME_NEW_YORK);

		City secondCity = new City();
		secondCity.setName(CITY_NAME_LA);

		firstCity = cityRepository.save(firstCity);
		secondCity = cityRepository.save(secondCity);

		Route route = new Route();
		route.setFirstCity(firstCity);
		route.setSecondCity(secondCity);
		route.setDistance(DISTANCE);
		routeRepository.save(route);

		// When
		List<Route> routeList = routeRepository.findRoutesByFirstCity(firstCity);

		// Then
		int expectedResultListSize = 1;
		assertThat(routeList).asList().size().isEqualTo(expectedResultListSize);
	}

	@Test
	void findNoRouteBetweenTwoCities() {
		// Given
		City firstCity = new City();
		firstCity.setName(CITY_NAME_NEW_YORK);

		City secondCity = new City();
		secondCity.setName(CITY_NAME_LA);

		firstCity = cityRepository.save(firstCity);
		secondCity = cityRepository.save(secondCity);

		// When
		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		// Then
		assertThat(optionalRoute.isPresent()).isFalse();
	}

	@Test
	void findRouteBetweenTwoCities() {
		// Given
		City firstCity = new City();
		firstCity.setName(CITY_NAME_NEW_YORK);

		City secondCity = new City();
		secondCity.setName(CITY_NAME_LA);

		firstCity = cityRepository.save(firstCity);
		secondCity = cityRepository.save(secondCity);

		Route route = new Route();
		route.setFirstCity(firstCity);
		route.setSecondCity(secondCity);
		route.setDistance(25f);
		routeRepository.save(route);

		// When
		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		// Then
		assertThat(optionalRoute.isPresent()).isTrue();
	}
}

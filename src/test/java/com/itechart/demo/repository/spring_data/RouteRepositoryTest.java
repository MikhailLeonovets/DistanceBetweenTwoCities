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

	@AfterEach
	void tearDown() {
		routeRepository.deleteAll();
		cityRepository.deleteAll();
	}

	@Test
	void findNoRoutesByFirstCity() {
		City city = new City();
		city.setName("New York");
		city = cityRepository.save(city);
		List<Route> routeList = routeRepository.findRoutesByFirstCity(city);

		assertThat(routeList).asList().isEmpty();
	}

	@Test
	void findOneRouteByFirstCity() {
		City firstCity = new City();
		firstCity.setName("New York");

		City secondCity = new City();
		secondCity.setName("LA");

		firstCity = cityRepository.save(firstCity);
		secondCity = cityRepository.save(secondCity);

		Route route = new Route();
		route.setFirstCity(firstCity);
		route.setSecondCity(secondCity);
		route.setDistance(25f);
		routeRepository.save(route);

		List<Route> routeList = routeRepository.findRoutesByFirstCity(firstCity);

		assertThat(routeList).asList().size().isEqualTo(1);
	}

	@Test
	void findNoRouteBetweenTwoCities() {
		City firstCity = new City();
		firstCity.setName("New York");

		City secondCity = new City();
		secondCity.setName("LA");

		firstCity = cityRepository.save(firstCity);
		secondCity = cityRepository.save(secondCity);

		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		assertThat(optionalRoute.isPresent()).isFalse();
	}

	@Test
	void findRouteBetweenTwoCities() {
		City firstCity = new City();
		firstCity.setName("New York");

		City secondCity = new City();
		secondCity.setName("LA");

		firstCity = cityRepository.save(firstCity);
		secondCity = cityRepository.save(secondCity);

		Route route = new Route();
		route.setFirstCity(firstCity);
		route.setSecondCity(secondCity);
		route.setDistance(25f);
		routeRepository.save(route);

		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());

		assertThat(optionalRoute.isPresent()).isTrue();
	}
}

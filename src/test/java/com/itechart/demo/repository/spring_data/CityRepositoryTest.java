package com.itechart.demo.repository.spring_data;

import com.itechart.demo.repository.entity.City;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class CityRepositoryTest {
	@Autowired
	private CityRepository cityRepository;

	@AfterEach
	void tearDown() {
		cityRepository.deleteAll();
	}

	@Test
	void itShouldReturnCity() {
		// Given
		String cityName = "New York";
		City city = new City();
		city.setName(cityName);

		// When
		cityRepository.save(city);

		// Then
		Optional<City> expectedResultOptionalCity = cityRepository.findByName(cityName);
		assertThat(expectedResultOptionalCity.isPresent()).isTrue();
	}
}

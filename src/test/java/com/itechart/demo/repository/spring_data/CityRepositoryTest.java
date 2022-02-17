package com.itechart.demo.repository.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.hibernate.config.HibernateUtil;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.HibernateOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
		String cityName = "New1 York";
		City city = new City();
		city.setName(cityName);
		cityRepository.save(city);
		Optional<City> expectedOptionalCity = cityRepository.findByName(cityName);
		assertThat(expectedOptionalCity.isPresent()).isTrue();
	}
}

package com.itechart.demo.service.impl.database_service.spring_data;

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
	void findRoutesByFirstCity() {
	}

	@Test
	void update() {
	}

	@Test
	void findRouteBetweenCities() {
	}

	@Test
	void deleteById() {
	}

	@Test
	void delete() {
	}
}
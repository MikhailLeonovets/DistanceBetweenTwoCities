package com.itechart.demo.service.impl.database_service.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.spring_data.CityRepository;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CitySpringDataServiceTest {
	@Mock
	private CityRepository cityRepository;
	private CitySpringDataService underTest;

	@BeforeEach
	void setUp() {
		underTest = new CitySpringDataService(cityRepository);
	}

	@Test
	void save() {
		City city = new City();
		city.setName("New York");
		underTest.save(city);
		ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
		Mockito.verify(cityRepository).save(cityArgumentCaptor.capture());
		City capturedCity = cityArgumentCaptor.getValue();
		AssertionsForClassTypes.assertThat(capturedCity).isEqualTo(city);
	}

	@Test
	void canFindAll() {
		underTest.findAll();
		Mockito.verify(cityRepository).findAll();
	}

	@Test
	void canFindByName() throws CityNotFoundException {
		String cityName = "New York";
		City city = new City();
		Mockito.doReturn(Optional.of(city)).when(cityRepository).findByName(ArgumentMatchers.any());
		City returnedCity = underTest.findByName(cityName);
		Mockito.verify(cityRepository).findByName(ArgumentMatchers.eq(cityName));
		Assertions.assertNotNull(returnedCity);
	}

	@Test
	void findByNameThrowsCityNotFoundException() {
		String cityName = "New York";
		Mockito.doReturn(Optional.empty()).when(cityRepository).findByName(ArgumentMatchers.any());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findByName(cityName))
				.isInstanceOf(CityNotFoundException.class);
	}

	@Test
	void findById() throws CityNotFoundException {
		Long id = 1L;
		City city = new City();
		Mockito.doReturn(Optional.of(city)).when(cityRepository).findById(ArgumentMatchers.any());
		City returnedCity = underTest.findById(id);
		Mockito.verify(cityRepository).findById(ArgumentMatchers.eq(id));
		Assertions.assertNotNull(returnedCity);
	}

	@Test
	void findByIdThrowsCityNotFoundException() {
		Long id = 1L;
		BDDMockito.given(cityRepository.findById(id)).willReturn(Optional.empty());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findById(id))
				.isInstanceOf(CityNotFoundException.class);
	}

	@Test
	void update() {
		City city = new City();
		underTest.update(city);
		Mockito.verify(cityRepository).save(city);
	}

	@Test
	void deleteById() {
		Long id = 1L;
		underTest.deleteById(id);
		Mockito.verify(cityRepository).deleteById(id);
	}

	@Test
	void delete() {
		City city = new City();
		underTest.delete(city);
		Mockito.verify(cityRepository).delete(city);
	}
}
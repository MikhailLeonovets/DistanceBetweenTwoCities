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

	private static final String CITY_NAME_NEW_YORK = "New York";
	private static final Long CITY_ID = 1L;

	@BeforeEach
	void setUp() {
		underTest = new CitySpringDataService(cityRepository);
	}

	@Test
	void testSave() {
		City city = new City();
		city.setName(CITY_NAME_NEW_YORK);
		underTest.save(city);
		ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
		Mockito.verify(cityRepository).save(cityArgumentCaptor.capture());
		City capturedCity = cityArgumentCaptor.getValue();
		AssertionsForClassTypes.assertThat(capturedCity).isEqualTo(city);
	}

	@Test
	void testFindAll() {
		underTest.findAll();
		Mockito.verify(cityRepository).findAll();
	}

	@Test
	void testFindByName() throws CityNotFoundException {
		City city = new City();
		city.setName(CITY_NAME_NEW_YORK);
		Mockito.doReturn(Optional.of(city)).when(cityRepository).findByName(ArgumentMatchers.any());
		City returnedCity = underTest.findByName(CITY_NAME_NEW_YORK);
		Mockito.verify(cityRepository).findByName(ArgumentMatchers.eq(CITY_NAME_NEW_YORK));
		Assertions.assertNotNull(returnedCity);
	}

	@Test
	void testFindByNameThrowsCityNotFoundException() {
		Mockito.doReturn(Optional.empty()).when(cityRepository).findByName(ArgumentMatchers.any());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findByName(CITY_NAME_NEW_YORK))
				.isInstanceOf(CityNotFoundException.class);
	}

	@Test
	void testFindById() throws CityNotFoundException {
		City city = new City();
		city.setId(CITY_ID);
		Mockito.doReturn(Optional.of(city)).when(cityRepository).findById(ArgumentMatchers.any());
		City returnedCity = underTest.findById(CITY_ID);
		Mockito.verify(cityRepository).findById(ArgumentMatchers.eq(CITY_ID));
		Assertions.assertNotNull(returnedCity);
	}

	@Test
	void testFindByIdThrowsCityNotFoundException() {
		BDDMockito.given(cityRepository.findById(CITY_ID)).willReturn(Optional.empty());
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.findById(CITY_ID))
				.isInstanceOf(CityNotFoundException.class);
	}

	@Test
	void testUpdate() {
		City city = new City();
		underTest.update(city);
		Mockito.verify(cityRepository).save(city);
	}

	@Test
	void testDeleteById() {
		underTest.deleteById(CITY_ID);
		Mockito.verify(cityRepository).deleteById(CITY_ID);
	}

	@Test
	void testDelete() {
		City city = new City();
		underTest.delete(city);
		Mockito.verify(cityRepository).delete(city);
	}
}
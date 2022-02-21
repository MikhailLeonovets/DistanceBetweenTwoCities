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
	private CitySpringDataService citySpringDataService;

	private static final String CITY_NAME_NEW_YORK = "New York";
	private static final Long CITY_ID = 1L;

	@BeforeEach
	void setUp() {
		citySpringDataService = new CitySpringDataService(cityRepository);
	}

	@Test
	void testSave() {
		// Given
		City city = new City();
		city.setName(CITY_NAME_NEW_YORK);

		// When
		citySpringDataService.save(city);

		// Then
		ArgumentCaptor<City> cityArgumentCaptor = ArgumentCaptor.forClass(City.class);
		Mockito.verify(cityRepository).save(cityArgumentCaptor.capture());
		City capturedCity = cityArgumentCaptor.getValue();
		AssertionsForClassTypes.assertThat(capturedCity).isEqualTo(city);
	}

	@Test
	void testFindAll() {
		// When
		citySpringDataService.findAll();

		// Then
		Mockito.verify(cityRepository).findAll();
	}

	@Test
	void testFindByName() throws CityNotFoundException {
		// Given
		City city = new City();
		city.setName(CITY_NAME_NEW_YORK);
		Mockito.doReturn(Optional.of(city)).when(cityRepository).findByName(ArgumentMatchers.any());

		// When
		City returnedCity = citySpringDataService.findByName(CITY_NAME_NEW_YORK);

		// Then
		Mockito.verify(cityRepository).findByName(ArgumentMatchers.eq(CITY_NAME_NEW_YORK));
		Assertions.assertNotNull(returnedCity);
	}

	@Test
	void testFindByNameThrowsCityNotFoundException() {
		// Given
		Mockito.doReturn(Optional.empty()).when(cityRepository).findByName(ArgumentMatchers.any());

		// When and Then
		Assertions.assertThrows(CityNotFoundException.class,
				() -> citySpringDataService.findByName(CITY_NAME_NEW_YORK));
	}

	@Test
	void testFindById() throws CityNotFoundException {
		// Given
		City city = new City();
		city.setId(CITY_ID);
		Mockito.doReturn(Optional.of(city)).when(cityRepository).findById(ArgumentMatchers.any());

		// When
		City returnedCity = citySpringDataService.findById(CITY_ID);

		// Then
		Mockito.verify(cityRepository).findById(ArgumentMatchers.eq(CITY_ID));
		Assertions.assertNotNull(returnedCity);
	}

	@Test
	void testFindByIdThrowsCityNotFoundException() {
		// Given
		BDDMockito.given(cityRepository.findById(CITY_ID)).willReturn(Optional.empty());

		//When and Then
		Assertions.assertThrows(CityNotFoundException.class, () -> citySpringDataService.findById(CITY_ID));
	}

	@Test
	void testUpdate() {
		// Given
		City city = new City();

		// When
		citySpringDataService.update(city);

		// Then
		Mockito.verify(cityRepository).save(city);
	}

	@Test
	void testDeleteById() {
		// When
		citySpringDataService.deleteById(CITY_ID);

		// Then
		Mockito.verify(cityRepository).deleteById(CITY_ID);
	}

	@Test
	void testDelete() {
		// Given
		City city = new City();

		// When
		citySpringDataService.delete(city);

		// Then
		Mockito.verify(cityRepository).delete(city);
	}
}
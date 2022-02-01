package com.itechart.demo.service.cache;

import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.exception.CityNotFoundException;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Optional;

@Setter
@Component
@Scope("singleton")
@ApplicationScope
public class CityCache {
	private final CityRepository cityRepository;

	private List<City> cities;

	public CityCache(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
		cities = cityRepository.findAll();
	}

	public List<City> getCities() {
		return cities;
	}

	public City getCityByName(String name) throws CityNotFoundException {
		Optional<City> optionalCity = cities.stream()
				.filter(city -> city.getName().equals(name)).findFirst();
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}
}

package com.itechart.demo.service.impl.cache_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.CityService;
import com.itechart.demo.cache.CityCache;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.cache.initializer.CityCacheInitializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("cityCacheService")
public class CityCacheService implements CityService {
	private final CityCache cityCache;

	public CityCacheService(CityCache cityCache, CityCacheInitializer cityCacheInitializer) {
		this.cityCache = cityCache;
		cityCacheInitializer.initCityCache();
	}

	@Override
	public List<City> findAll() {
		return cityCache.getCities();
	}

	@Override
	public City findByName(String name) throws CityNotFoundException {
		Optional<City> optionalCity = cityCache.getCities().stream()
				.filter(city -> city.getName().equals(name))
				.findFirst();
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}

	@Override
	public City findById(Long id) throws CityNotFoundException {
		Optional<City> optionalCity = cityCache.getCities().stream()
				.filter(city -> city.getId().equals(id))
				.findFirst();
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}
}

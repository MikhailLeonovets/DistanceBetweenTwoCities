package com.itechart.demo.cache.initializer;

import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.cache.CityCache;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class CityCacheInitializer {
	private final CityCache cityCache;
	private final CityRepository cityRepository;

	public CityCacheInitializer(CityCache cityCache, CityRepository cityRepository) {
		this.cityCache = cityCache;
		this.cityRepository = cityRepository;
	}

	public void initCityCache() {
		cityCache.setCities(cityRepository.findAll());
	}
}

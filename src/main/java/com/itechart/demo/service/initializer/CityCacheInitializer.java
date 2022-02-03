package com.itechart.demo.service.initializer;

import com.itechart.demo.service.CityService;
import com.itechart.demo.service.cache.CityCache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class CityCacheInitializer {
	private final CityCache cityCache;
	private final CityService cityService;

	public CityCacheInitializer(CityCache cityCache, @Qualifier("cityServiceImpl") CityService cityService) {
		this.cityCache = cityCache;
		this.cityService = cityService;
	}

	public void initCityCache() {
		cityCache.setCities(cityService.findAll());
	}
}

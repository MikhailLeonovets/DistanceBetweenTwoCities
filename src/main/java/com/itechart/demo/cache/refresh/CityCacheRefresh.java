package com.itechart.demo.cache.refresh;

import com.itechart.demo.cache.CityCache;
import com.itechart.demo.repository.CityRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CityCacheRefresh {
	private final CityCache cityCache;
	private final CityRepository cityRepository;

	public CityCacheRefresh(CityCache cityCache, CityRepository cityRepository) {
		this.cityCache = cityCache;
		this.cityRepository = cityRepository;
	}

	@Scheduled(cron = "0 0 12 * * ?")
	public void refreshCityCache() {
		cityCache.setCities(cityRepository.findAll());
	}
}

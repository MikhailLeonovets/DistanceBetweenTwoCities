package com.itechart.demo.cache.refresher;

import com.itechart.demo.cache.CityCache;
import com.itechart.demo.repository.spring_data.CityRepository;
import com.itechart.demo.service.impl.cache_service.CityCacheService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(CityCacheService.class)
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

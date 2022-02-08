package com.itechart.demo.config;

import com.itechart.demo.cache.CityCache;
import com.itechart.demo.cache.RouteCache;
import com.itechart.demo.cache.initializer.CityCacheInitializer;
import com.itechart.demo.cache.initializer.RouteCacheInitializer;
import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.impl.cache_service.CityCacheService;
import com.itechart.demo.service.impl.cache_service.RouteCacheService;
import com.itechart.demo.service.impl.database_service.spring_data.CitySpringDataService;
import com.itechart.demo.service.impl.database_service.spring_data.RouteSpringDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/cache_configurations.properties")
public class DatasourceBeanConfiguration {
	private final CityRepository cityRepository;
	private final RouteRepository routeRepository;

	@Value("${city.cache.enable}")
	private boolean cityCacheEnabled;

	@Value("${route.cache.enable}")
	private boolean routeCacheEnabled;

	public DatasourceBeanConfiguration(CityRepository cityRepository, RouteRepository routeRepository) {
		this.cityRepository = cityRepository;
		this.routeRepository = routeRepository;
	}


	@Bean
	public CityService cityService() {
		if (cityCacheEnabled) {
			CityCache cityCache = new CityCache();
			CityCacheInitializer cityCacheInitializer = new CityCacheInitializer(cityCache, cityRepository);
			return new CityCacheService(cityCache, cityCacheInitializer);
		} else {
			return new CitySpringDataService(cityRepository);
		}
	}

	@Bean
	public RouteService routeService() {
		if (routeCacheEnabled) {
			RouteCache routeCache = new RouteCache();
			RouteCacheInitializer routeCacheInitializer = new RouteCacheInitializer(routeCache, routeRepository);
			return new RouteCacheService(routeCache, routeCacheInitializer);
		} else {
			return new RouteSpringDataService(routeRepository);
		}
	}
}

package com.itechart.demo.config;

import com.itechart.demo.cache.CityCache;
import com.itechart.demo.cache.RouteCache;
import com.itechart.demo.cache.initializer.CityCacheInitializer;
import com.itechart.demo.cache.initializer.RouteCacheInitializer;
import com.itechart.demo.repository.entity.jdbc_template.DataSourceProvider;
import com.itechart.demo.repository.entity.jdbc_template.impl.CityJdbcTemplateRepository;
import com.itechart.demo.repository.entity.jdbc_template.impl.RouteJdbcTemplateRepository;
import com.itechart.demo.repository.hibernate.CityHibernateRepository;
import com.itechart.demo.repository.hibernate.RouteHibernateRepository;
import com.itechart.demo.repository.spring_data.CityRepository;
import com.itechart.demo.repository.spring_data.RouteRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.impl.cache_service.CityCacheService;
import com.itechart.demo.service.impl.cache_service.RouteCacheService;
import com.itechart.demo.service.impl.database_service.hibernate.CityHibernateService;
import com.itechart.demo.service.impl.database_service.hibernate.RouteHibernateService;
import com.itechart.demo.service.impl.database_service.jdbc_template.CityJdbcService;
import com.itechart.demo.service.impl.database_service.jdbc_template.RouteJdbcService;
import com.itechart.demo.service.impl.database_service.spring_data.CitySpringDataService;
import com.itechart.demo.service.impl.database_service.spring_data.RouteSpringDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/service.properties")
public class DataSourceServicesBeanConfiguration {
	private final CityRepository cityRepository;
	private final RouteRepository routeRepository;

	@Value("${city.cache.enable}")
	private boolean cityCacheEnabled;
	@Value("${route.cache.enable}")
	private boolean routeCacheEnabled;
	@Value("${city.service}")
	private String cityServiceTechnology;
	@Value("${route.service}")
	private String routeServiceTechnology;

	public DataSourceServicesBeanConfiguration(CityRepository cityRepository, RouteRepository routeRepository) {
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
			if ("spring_data".equals(cityServiceTechnology)) {
				return new CitySpringDataService(cityRepository);
			}
			if ("hibernate".equals(cityServiceTechnology)) {
				return new CityHibernateService(new CityHibernateRepository());
			}
			if ("jdbc_template".equals(cityServiceTechnology)) {
				return new CityJdbcService(new CityJdbcTemplateRepository(new DataSourceProvider()));
			}
		}
		return new CitySpringDataService(cityRepository);
	}

	@Bean
	public RouteService routeService() {
		if (routeCacheEnabled) {
			RouteCache routeCache = new RouteCache();
			RouteCacheInitializer routeCacheInitializer = new RouteCacheInitializer(routeCache, routeRepository);
			return new RouteCacheService(routeCache, routeCacheInitializer);
		} else {
			if ("spring_data".equals(routeServiceTechnology)) {
				return new RouteSpringDataService(routeRepository);
			}
			if ("hibernate".equals(routeServiceTechnology)) {
				return new RouteHibernateService(new RouteHibernateRepository());
			}
			if ("jdbc_template".equals(routeServiceTechnology)) {
				return new RouteJdbcService(new RouteJdbcTemplateRepository(new DataSourceProvider()));
			}
		}
		return new RouteSpringDataService(routeRepository);
	}
}

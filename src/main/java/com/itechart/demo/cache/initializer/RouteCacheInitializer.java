package com.itechart.demo.cache.initializer;

import com.itechart.demo.repository.spring_data.RouteRepository;
import com.itechart.demo.cache.RouteCache;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope("singleton")
public class RouteCacheInitializer {
	private final RouteCache routeCache;
	private final RouteRepository routeRepository;

	public RouteCacheInitializer(RouteCache routeCache, RouteRepository routeRepository) {
		this.routeCache = routeCache;
		this.routeRepository = routeRepository;
	}

	public void initRouteCache() {
		routeCache.setRoutes(routeRepository.findAll());
	}
}

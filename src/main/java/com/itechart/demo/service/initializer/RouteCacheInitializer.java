package com.itechart.demo.service.initializer;

import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.cache.RouteCache;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Component
@Scope("singleton")
public class RouteCacheInitializer {
	private final RouteCache routeCache;
	private final RouteService routeService;

	public RouteCacheInitializer(RouteCache routeCache, @Qualifier("routeServiceImpl") RouteService routeService) {
		this.routeCache = routeCache;
		this.routeService = routeService;
	}

	public void initRouteCache() {
		routeCache.setRoutes(routeService.findALl());
	}
}

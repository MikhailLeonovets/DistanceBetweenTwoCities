package com.itechart.demo.cache.refresh;

import com.itechart.demo.cache.RouteCache;
import com.itechart.demo.repository.RouteRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RouteCacheRefresh {
	private final RouteCache routeCache;
	private final RouteRepository routeRepository;

	public RouteCacheRefresh(RouteCache routeCache, RouteRepository routeRepository) {
		this.routeCache = routeCache;
		this.routeRepository = routeRepository;
	}

	@Scheduled(cron = "0 0 12 * * ?")
	public void refreshRouteCache() {
		routeCache.setRoutes(routeRepository.findAll());
	}
}

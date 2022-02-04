package com.itechart.demo.cache.refresher;

import com.itechart.demo.cache.RouteCache;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.impl.cache_service.RouteCacheService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(RouteCacheService.class)
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

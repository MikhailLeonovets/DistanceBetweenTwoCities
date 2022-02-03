package com.itechart.demo.service.impl.cache_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.cache.RouteCache;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.initializer.RouteCacheInitializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("routeCacheService")
public class RouteCacheService implements RouteService {
	private final RouteCache routeCache;

	public RouteCacheService(RouteCache routeCache, RouteCacheInitializer routeCacheInitializer) {
		this.routeCache = routeCache;
		routeCacheInitializer.initRouteCache();
	}

	@Override
	public List<Route> findALl() {
		return routeCache.getRoutes();
	}

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException {
		List<Route> routes = routeCache.getRoutes().stream()
				.filter(route -> route.getFirstCity().equals(firstCity))
				.collect(Collectors.toList());
		if (routes.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return routes;
	}

	@Override
	public Route findRouteBetweenCities(City firstCity, City secondCity) throws RouteNotFoundException {
		Optional<Route> optionalRoute = routeCache.getRoutes().stream()
				.filter(route1 -> route1.getFirstCity().equals(firstCity)
						&& route1.getSecondCity().equals(secondCity)).findAny();
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}
}

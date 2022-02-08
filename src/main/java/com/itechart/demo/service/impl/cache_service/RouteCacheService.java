package com.itechart.demo.service.impl.cache_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.cache.RouteCache;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.cache.initializer.RouteCacheInitializer;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("routeCacheService")
public class RouteCacheService implements RouteService {
	private final RouteCache routeCache;
	private final RouteCacheInitializer routeCacheInitializer;

	public RouteCacheService(RouteCache routeCache, RouteCacheInitializer routeCacheInitializer) {
		this.routeCache = routeCache;
		this.routeCacheInitializer = routeCacheInitializer;
	}

	@Override
	public Route save(Route route) {
		routeCache.getRoutes().add(route);
		return route;
	}

	@Override
	public List<Route> findAll() {
		return routeCache.getRoutes();
	}

	@Override
	public Route findById(Long id) throws RouteNotFoundException {
		Optional<Route> optionalRoute = routeCache.getRoutes().stream()
				.filter(route -> route.getId().equals(id))
				.findAny();
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
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
	public Route update(Route route) {
		return null; //TODO
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

	@Override
	public void deleteById(Long id) throws RouteNotFoundException {
		routeCache.getRoutes().remove(findById(id));
	}

	@Override
	public void delete(Route route) {
		routeCache.getRoutes().remove(route);
	}

	@PostConstruct
	private void initRouteCache() {
		routeCacheInitializer.initRouteCache();
	}
}

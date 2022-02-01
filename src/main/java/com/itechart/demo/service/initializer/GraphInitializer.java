package com.itechart.demo.service.initializer;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.cache.CityCache;
import com.itechart.demo.service.cache.RouteCache;
import com.itechart.demo.service.model.Graph;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Component
@Scope("singleton")
public class GraphInitializer {
	private final Graph graph;
	private final CityCache cityCache;
	private final RouteCache routeCache;

	public GraphInitializer(Graph graphCity, CityCache cityCache, RouteCache routeCache) {
		this.graph = graphCity;
		this.cityCache = cityCache;
		this.routeCache = routeCache;

		Map<String, LinkedHashSet<String>> graph = new HashMap<>();
		for (City city : cityCache.getCities()) {
			LinkedHashSet<String> adjacentCities = new LinkedHashSet<>();
			for (Route route : routeCache.getRoutes().stream()
					.filter(route -> route.getFirstCity().equals(city))
					.collect(Collectors.toList())) {
				adjacentCities.add(route.getSecondCity().getName());
			}
			graph.put(city.getName(), adjacentCities);
		}
		this.graph.setGraph(graph);
	}
}

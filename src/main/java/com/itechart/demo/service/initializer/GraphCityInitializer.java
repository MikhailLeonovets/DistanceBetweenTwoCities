package com.itechart.demo.service.initializer;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.model.Graph;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Component
@Scope("singleton")
public class GraphCityInitializer {
	private final Graph graphCity;
	private final CityService cityService;
	private final RouteService routeService;

	public GraphCityInitializer(Graph graphCity,
	                            @Qualifier("cityCacheService") CityService cityService,
	                            @Qualifier("routeCacheService") RouteService routeService) {
		this.graphCity = graphCity;
		this.cityService = cityService;
		this.routeService = routeService;


		Map<String, LinkedHashSet<String>> graph = new HashMap<>();
		for (City city : cityService.findAll()) {
			LinkedHashSet<String> adjacentCities = new LinkedHashSet<>();
			for (Route route : routeService.findALl().stream()
					.filter(route -> route.getFirstCity().equals(city)).toList()) {
				adjacentCities.add(route.getSecondCity().getName());
			}
			graph.put(city.getName(), adjacentCities);
		}
		this.graphCity.setGraph(graph);
	}
}

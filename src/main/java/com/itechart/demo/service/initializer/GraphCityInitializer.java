package com.itechart.demo.service.initializer;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.model.GraphCity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Component
@Scope("singleton")
public class GraphCityInitializer {
	private final GraphCity graphCity;
	private final CityService cityService;
	private final RouteService routeService;

	public GraphCityInitializer(GraphCity graphCity, CityService cityService, RouteService routeService) {
		this.graphCity = graphCity;
		this.cityService = cityService;
		this.routeService = routeService;
	}

	public void initGraphCity() {
		Map<City, LinkedHashSet<Route>> graph = new HashMap<>();
		for (City city : cityService.findAll()) {
			graph.put(city, new LinkedHashSet<>(routeService.findRoutesByFirstCity(city)));
		}
		graphCity.setGraph(graph);
	}
}

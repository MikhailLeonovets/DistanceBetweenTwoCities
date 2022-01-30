package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.GraphService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.GraphCity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@Service
public class GraphServiceImpl implements GraphService {
	private final CityService cityService;
	private final RouteService routeService;
	private final GraphCity graphCity;

	public GraphServiceImpl(CityService cityService, RouteService routeService, GraphCity graphCity) {
		this.cityService = cityService;
		this.routeService = routeService;
		this.graphCity = graphCity;
	}

	@Override
	public void initGraphCity() {
		Map<City, LinkedHashSet<Route>> graph = new HashMap<>();
		for (City city : cityService.findAll()) {
			graph.put(city, new LinkedHashSet<>(routeService.findRoutesByFirstCity(city)));
		}
		graphCity.setGraph(graph);
	}

	@Override
	public HashSet<City> getAdjacentCities(City city) {
		if (graphCity.getGraph().isEmpty()) {
			initGraphCity();
		}
		HashSet<City> adjacentCities = new HashSet<>();
		for (Route adjacentRoute : graphCity.getGraph().get(city)) {
			adjacentCities.add(adjacentRoute.getSecondCity());
		}
		return adjacentCities;
	}

}

package com.itechart.demo.service.impl.repository_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.initializer.GraphCityInitializer;
import com.itechart.demo.service.model.Graph;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Getter
@Setter
@Service
public class GraphCityServiceImpl {
	private final Graph graph;
	private final GraphCityInitializer graphCityInitializer;
	private final CityService cityService;
	private final RouteService routeService;

	public GraphCityServiceImpl(Graph graphCity, GraphCityInitializer graphCityInitializer,
	                            @Qualifier("cityCacheService") CityService cityService,
	                            @Qualifier("routeCacheService") RouteService routeService) {
		this.graph = graphCity;
		this.graphCityInitializer = graphCityInitializer;
		this.cityService = cityService;

		this.routeService = routeService;
	}

	public HashSet<City> getAdjacentCities(City city) throws CityNotFoundException {
		HashSet<City> cities = new HashSet<>();
		for (String s : graph.getGraph().get(city.getName())) {
			City cityByName = cityService.findByName(s);
			cities.add(cityByName);
		}
		return cities;
	}
}

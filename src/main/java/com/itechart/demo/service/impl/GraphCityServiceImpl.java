package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.GraphCityService;
import com.itechart.demo.service.cache.CityCache;
import com.itechart.demo.service.cache.RouteCache;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.initializer.GraphInitializer;
import com.itechart.demo.service.model.Graph;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Getter
@Setter
@Service
public class GraphCityServiceImpl implements GraphCityService {
	private final Graph graph;
	private final GraphInitializer graphCityInitializer;
	private final CityCache cityCache;
	private final RouteCache routeCache;

	public GraphCityServiceImpl(Graph graphCity, GraphInitializer graphCityInitializer,
	                            CityCache cityCache, RouteCache routeCache) {
		this.graph = graphCity;
		this.graphCityInitializer = graphCityInitializer;
		this.cityCache = cityCache;
		this.routeCache = routeCache;
	}

	@Override
	public HashSet<City> getAdjacentCities(City city) throws CityNotFoundException {
		HashSet<City> cities = new HashSet<>();
		for (String s : graph.getGraph().get(city.getName())) {
			City cityByName = cityCache.getCityByName(s);
			cities.add(cityByName);
		}
		return cities;
	}
}

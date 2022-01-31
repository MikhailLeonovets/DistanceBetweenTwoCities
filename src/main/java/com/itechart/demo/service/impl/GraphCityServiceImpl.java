package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.GraphCityService;
import com.itechart.demo.service.initializer.GraphCityInitializer;
import com.itechart.demo.service.model.GraphCity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Getter
@Setter
@Service
public class GraphCityServiceImpl implements GraphCityService {
	private final GraphCity graphCity;
	private final GraphCityInitializer graphCityInitializer;

	public GraphCityServiceImpl(GraphCity graphCity, GraphCityInitializer graphCityInitializer) {
		this.graphCity = graphCity;
		this.graphCityInitializer = graphCityInitializer;
	}

	@Override
	public HashSet<City> getAdjacentCities(City city) {
		if (graphCity.getGraph().isEmpty()) {
			graphCityInitializer.initGraphCity();
		}
		HashSet<City> adjacentCities = new HashSet<>();
		for (Route adjacentRoute : graphCity.getGraph().get(city)) {
			adjacentCities.add(adjacentRoute.getSecondCity());
		}
		return adjacentCities;
	}
}

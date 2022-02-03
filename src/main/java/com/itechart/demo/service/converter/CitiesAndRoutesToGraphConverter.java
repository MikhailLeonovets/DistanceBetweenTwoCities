package com.itechart.demo.service.converter;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.model.Graph;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Component
@Scope("singleton")
public class CitiesAndRoutesToGraphConverter {

	public CitiesAndRoutesToGraphConverter() {
	}

	public Graph convert(List<City> cities, List<Route> routes) {
		Map<String, LinkedHashSet<String>> graph = new HashMap<>();
		for (City city : cities) {
			LinkedHashSet<String> adjacentCities = new LinkedHashSet<>();
			for (Route route : routes.stream()
					.filter(route -> route.getFirstCity().equals(city)).toList()) {
				adjacentCities.add(route.getSecondCity().getName());
			}
			graph.put(city.getName(), adjacentCities);
		}
		return new Graph(graph);
	}
}

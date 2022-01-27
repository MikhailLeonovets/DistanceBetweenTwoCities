package com.itechart.demo.service.model;

import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.exception.RouteNotFoundException;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Component
@Scope("singleton")
public class Graph {
	private final CityRepository cityRepository;
	private final RouteRepository routeRepository;

	private List<City> cities;
	private List<Route> routes;

	public Graph(CityRepository cityRepository, RouteRepository routeRepository) {
		this.cityRepository = cityRepository;
		this.routeRepository = routeRepository;
	}

	public void init() {
		cities = cityRepository.findAll();
		routes = routeRepository.findAll();
	}

	public LinkedList<City> getAdjacentCities(City city) {
		List<Route> adjacentRoutes = routes.stream()
				.filter(route -> route.getFirstCity().equals(city))
				.collect(Collectors.toList());
		LinkedList<City> adjacentCities = new LinkedList<>();
		for (Route route : adjacentRoutes) {
			adjacentCities.add(route.getSecondCity());
		}
		return adjacentCities;
	}

	public Route findRouteBetweenCities(Long firstCityId, Long secondCityId) throws RouteNotFoundException {
		Optional<Route> optionalRoute = routes.stream()
				.filter(route -> Objects.equals(route.getFirstCity().getId(), firstCityId)
						&& Objects.equals(route.getSecondCity().getId(), secondCityId))
				.findAny();
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}

}

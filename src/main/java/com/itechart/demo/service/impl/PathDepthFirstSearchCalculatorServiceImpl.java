package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.GraphCityService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.initializer.GraphCityInitializer;
import com.itechart.demo.service.model.Path;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.exception.PathNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.ArrayList;

@Service
public class PathDepthFirstSearchCalculatorServiceImpl implements PathDepthFirstSearchCalculatorService {
	private final GraphCityService graphService;
	private final RouteService routeService;

	private Set<Path> paths;

	public PathDepthFirstSearchCalculatorServiceImpl(GraphCityService graphService, RouteService routeService) {
		this.graphService = graphService;
		this.routeService = routeService;
	}

	@Override
	public Set<Path> calculatePaths(City firstCity, City secondCity) throws PathNotFoundException,
			RouteNotFoundException {
		paths = new HashSet<>();
		LinkedList<City> visitedCities = new LinkedList<>();
		visitedCities.add(firstCity);
		depthFirstSearch(visitedCities, secondCity);
		return paths;
	}

	private void depthFirstSearch(LinkedList<City> visited, City lastCity) throws PathNotFoundException,
			RouteNotFoundException {
		HashSet<City> adjacentCities = graphService.getAdjacentCities(visited.getLast());
		for (City city : adjacentCities) {
			if (visited.contains(city)) {
				continue;
			}
			if (city.equals(lastCity)) {
				visited.add(city);
				paths.add(savePath(visited));
				visited.removeLast();
				break;
			}
		}
		for (City city : adjacentCities) {
			if (visited.contains(city) || city.equals(lastCity)) {
				continue;
			}
			visited.addLast(city);
			depthFirstSearch(visited, lastCity);
			visited.removeLast();
		}
	}

	private Path savePath(LinkedList<City> visited) throws PathNotFoundException, RouteNotFoundException {
		Path path = new Path();
		Float totalDistance = 0f;
		Set<Route> routes = new HashSet<>();
		ArrayList<City> cities = new ArrayList<>(visited);
		if (cities.size() == 0) {
			throw new PathNotFoundException();
		}
		for (int i = 1; i < cities.size(); i++) {
			Route route = routeService.findRouteBetweenCities(visited.get(i - 1), visited.get(i));
			totalDistance += route.getDistance();
			routes.add(route);
		}
		path.setRoutesToEndPoint(routes);
		path.setTotalDistance(totalDistance);
		return path;
	}
}

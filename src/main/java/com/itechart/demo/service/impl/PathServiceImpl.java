package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.PathService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.converter.CitiesAndRoutesToGraphConverter;
import com.itechart.demo.service.model.Graph;
import com.itechart.demo.service.model.Path;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Service
public class PathServiceImpl implements PathService {
	private final PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService;
	private final CitiesAndRoutesToGraphConverter graphCityInitializer;
	private final RouteService routeService;
	private final CityService cityService;

	public PathServiceImpl(PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService,
	                       CitiesAndRoutesToGraphConverter graphCityInitializer,
	                       @Qualifier("routeCacheService") RouteService routeService,
	                       @Qualifier("cityCacheService") CityService cityService) {
		this.pathDepthFirstSearchCalculatorService = pathDepthFirstSearchCalculatorService;
		this.graphCityInitializer = graphCityInitializer;
		this.routeService = routeService;
		this.cityService = cityService;
	}

	@Override
	public Set<Path> getPaths(City firstCity, City secondCity) throws PathNotFoundException, RouteNotFoundException,
			CityNotFoundException {
		Graph graphCity = graphCityInitializer.convert(cityService.findAll(), routeService.findALl());
		Set<LinkedList<String>> stringPaths = pathDepthFirstSearchCalculatorService.calculatePaths(graphCity,
				firstCity.getName(),
				secondCity.getName());
		Set<Path> paths = new HashSet<>();
		for (LinkedList<String> stringPath : stringPaths) {
			paths.add(getPathFromNodes(stringPath));
		}
		if (paths.isEmpty()) {
			throw new PathNotFoundException();
		}
		return paths;
	}

	private Path getPathFromNodes(LinkedList<String> nodes) throws RouteNotFoundException, CityNotFoundException {
		Path path = new Path();
		Float totalDistance = 0f;
		Set<Route> routes = new HashSet<>();
		for (int i = 1; i < nodes.size(); i++) {
			Route route = routeService.findRouteBetweenCities(cityService.findByName(nodes.get(i - 1)),
					cityService.findByName(nodes.get(i)));
			totalDistance += route.getDistance();
			routes.add(route);
		}
		path.setRoutesToEndPoint(routes);
		path.setTotalDistance(totalDistance);
		return path;
	}
}

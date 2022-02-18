package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.PathService;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.converter.CitiesAndRoutesToGraphConverter;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Graph;
import com.itechart.demo.service.model.Path;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Service
public class PathServiceImpl implements PathService {
	private final PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService;
	private final CitiesAndRoutesToGraphConverter citiesAndRoutesToGraphConverter;
	private final RouteService routeService;
	private final CityService cityService;

	public PathServiceImpl(PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService,
	                       CitiesAndRoutesToGraphConverter graphCityInitializer,
	                       RouteService routeService,
	                       CityService cityService) {
		this.pathDepthFirstSearchCalculatorService = pathDepthFirstSearchCalculatorService;
		this.citiesAndRoutesToGraphConverter = graphCityInitializer;
		this.routeService = routeService;
		this.cityService = cityService;
	}

	@Override
	public Set<Path> getPaths(Long firstCityId, Long secondCityId) throws PathNotFoundException, RouteNotFoundException,
			CityNotFoundException, GraphNullException {
		Graph graphCity = citiesAndRoutesToGraphConverter.convert(cityService.findAll(), routeService.findAll());
		City firstCity = cityService.findById(firstCityId);
		City secondCity = cityService.findById(secondCityId);
		Set<LinkedList<String>> stringPaths = pathDepthFirstSearchCalculatorService.calculatePaths(graphCity,
				firstCity.getName(),
				secondCity.getName());
		if (stringPaths.isEmpty()) {
			throw new PathNotFoundException();
		}
		Set<Path> paths = new HashSet<>();
		for (LinkedList<String> stringPath : stringPaths) {
			paths.add(convertNodesToPath(stringPath));
		}
		return paths;
	}

	private Path convertNodesToPath(LinkedList<String> nodes) throws RouteNotFoundException, CityNotFoundException {
		Path path = new Path();
		Float totalDistance = 0f;
		Set<Route> routes = new HashSet<>();
		for (int i = 1; i < nodes.size(); i++) {
			City firstCity = cityService.findByName(nodes.get(i - 1));
			City secondCity = cityService.findByName(nodes.get(i));
			Route route = routeService.findRouteBetweenCities(firstCity, secondCity);
			totalDistance += route.getDistance();
			routes.add(route);
		}
		path.setRoutesToEndPoint(routes);
		path.setTotalDistance(totalDistance);
		return path;
	}
}

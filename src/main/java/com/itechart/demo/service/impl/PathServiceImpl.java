package com.itechart.demo.service.impl;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.model.entity.Path;
import com.itechart.demo.model.entity.Route;
import com.itechart.demo.model.payload.response.PathVariant;
import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.PathService;
import com.itechart.demo.service.exception.PathNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class PathServiceImpl implements PathService {
	private final CityRepository cityRepository;
	private final RouteRepository routeRepository;

	private List<City> cities;
	private List<Route> routes;
	private Set<Path> paths;
	private PathVariant pathVariant;

	@Override
	public PathVariant getPaths(City firstCity, City secondCity) throws PathNotFound {
		init();
		LinkedList<City> visitedCities = new LinkedList<>();
		visitedCities.add(firstCity);
		depthFirst(visitedCities, secondCity);
		pathVariant.setPaths(paths);
		return pathVariant;
	}

	private void init() {
		pathVariant = new PathVariant();
		cities = cityRepository.findAll();
		routes = routeRepository.findAll();
		paths = new HashSet<>();
	}

	private void depthFirst(LinkedList<City> visited, City lastCity) throws PathNotFound {
		LinkedList<City> adjacentCities = new LinkedList<>();
		for (Route route : routeRepository.findRoutesByFirstCity(visited.getLast())) {
			adjacentCities.add(route.getSecondCity());
		}
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
			depthFirst(visited, lastCity);
			visited.removeLast();
		}
	}

	private Path savePath(LinkedList<City> visited) throws PathNotFound {
		Path path = new Path();
		Float totalDistance = 0f;
		Set<Route> routes = new HashSet<>();
		ArrayList<City> cities = new ArrayList<>(visited);
		if (cities.size() == 0) {
			throw new PathNotFound();
		}
		for (int i = 1; i < cities.size(); i++) {
			Route route = routeRepository.findByFirstCityIdAndSecondCityId(visited.get(i - 1).getId(), visited.get(i).getId()).get();
			totalDistance += route.getDistance();
			routes.add(route);
		}
		path.setRoutesToEndPoint(routes);
		path.setTotalDistance(totalDistance);
		return path;
	}
}

package com.itechart.demo.service.impl.repository_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("routeServiceImpl")
public class RouteDatabaseService implements RouteService {
	private final RouteRepository routeRepository;

	public RouteDatabaseService(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	@Override
	public List<Route> findALl() {
		return routeRepository.findAll();
	}

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException {
		List<Route> routes = routeRepository.findRoutesByFirstCity(firstCity);
		if (routes.isEmpty()){
			throw new RouteNotFoundException();
		}
		return routes;
	}

	@Override
	public Route findRouteBetweenCities(City firstCity, City secondCity)
			throws RouteNotFoundException {
		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}
}
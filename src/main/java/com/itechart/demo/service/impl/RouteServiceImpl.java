package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {
	private final RouteRepository routeRepository;

	public RouteServiceImpl(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) {
		return routeRepository.findRoutesByFirstCity(firstCity);
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

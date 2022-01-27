package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

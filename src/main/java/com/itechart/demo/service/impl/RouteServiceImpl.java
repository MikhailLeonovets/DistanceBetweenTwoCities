package com.itechart.demo.service.impl;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.model.entity.Route;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteServiceImpl implements RouteService {
	private final RouteRepository routeRepository;

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) {
		return routeRepository.findRoutesByFirstCity(firstCity);
	}
}

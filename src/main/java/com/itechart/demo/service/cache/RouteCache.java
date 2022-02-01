package com.itechart.demo.service.cache;

import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.repository.entity.Route;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Setter
@Component
@Scope("singleton")
@ApplicationScope
public class RouteCache {
	private final RouteRepository routeRepository;

	private List<Route> routes;

	public RouteCache(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
		routes = routeRepository.findAll();
	}

	public List<Route> getRoutes() {
		return routes;
	}
}

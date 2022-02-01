package com.itechart.demo.service.cash;

import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.repository.entity.Route;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
@Scope("singleton")
public class RouteCash {
	private final RouteRepository routeRepository;

	private List<Route> routes;

	public RouteCash(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	public void init() {
		this.routes = routeRepository.findAll();
	}
}

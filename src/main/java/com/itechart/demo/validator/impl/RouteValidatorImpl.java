package com.itechart.demo.validator.impl;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.spring_data.RouteRepository;
import com.itechart.demo.validator.RouteValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Optional;

@Component
@Scope("singleton")
@ApplicationScope
public class RouteValidatorImpl implements RouteValidator {
	private final RouteRepository repository;

	public RouteValidatorImpl(RouteRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean checkRouteExists(Route route) {
		Optional<Route> optionalRoute = repository.findByFirstCityIdAndSecondCityId(route.getFirstCity().getId(),
				route.getSecondCity().getId());
		return optionalRoute.isPresent();
	}
}

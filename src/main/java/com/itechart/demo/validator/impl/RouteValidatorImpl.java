package com.itechart.demo.validator.impl;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.validator.RouteValidator;
import com.itechart.demo.validator.exception.RouteExistsValidationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@Component
@Scope("singleton")
@ApplicationScope
public class RouteValidatorImpl implements RouteValidator {
	private final RouteService routeService;

	public RouteValidatorImpl(RouteService routeService) {
		this.routeService = routeService;
	}

	@Override
	public boolean checkDoesRouteExist(Route route) throws RouteExistsValidationException {
		try {
			routeService.findRouteBetweenCities(route.getFirstCity(), route.getSecondCity());
		} catch (RouteNotFoundException e) {
			return false;
		}
		throw new RouteExistsValidationException();
	}
}

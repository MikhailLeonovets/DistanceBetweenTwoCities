package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.togglz.RouteValidationFeature;
import com.itechart.demo.validator.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class RouteService {
	@Autowired
	private RouteValidator routeValidator;

	public abstract Route saveInDataBase(Route route);

	public Route save(Route route) {
		if (RouteValidationFeature.VALIDATION_FEATURE.isActive()) {
			if (routeValidator.checkRouteExists(route)) {
				return route;
			}
		}
		return saveInDataBase(route);
	}

	public abstract List<Route> findAll();

	public abstract Route findById(Long id) throws RouteNotFoundException;

	public abstract List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException;

	public abstract Route update(Route route);

	public abstract Route findRouteBetweenCities(City firstCity, City secondCity) throws RouteNotFoundException;

	public abstract void deleteById(Long id) throws RouteNotFoundException;

	public abstract void delete(Route route);

}

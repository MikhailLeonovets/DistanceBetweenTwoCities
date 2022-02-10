package com.itechart.demo.service.impl.database_service.jdbc_template;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.jdbc_template.impl.RouteJdbcTemplateRepository;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;

import java.util.List;
import java.util.Optional;

public class RouteJdbcService extends RouteService {
	private final RouteJdbcTemplateRepository repository;

	public RouteJdbcService(RouteJdbcTemplateRepository repository) {
		this.repository = repository;
	}

	@Override
	public Route saveInDataBase(Route route) {
		return repository.save(route);
	}

	@Override
	public List<Route> findAll() {
		return repository.findAll();
	}

	@Override
	public Route findById(Long id) throws RouteNotFoundException {
		Optional<Route> optionalRoute = repository.findById(id);
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException {
		List<Route> routes = repository.findByFirstCity(firstCity);
		if (routes.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return routes;
	}

	@Override
	public Route update(Route route) {
		return repository.update(route);
	}

	@Override
	public Route findRouteBetweenCities(City firstCity, City secondCity) throws RouteNotFoundException {
		Optional<Route> optionalRoute = repository.findRouteBetweenCities(firstCity, secondCity);
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}

	@Override
	public void deleteById(Long id) throws RouteNotFoundException {
		repository.deleteById(id);
	}

	@Override
	public void delete(Route route) {
		repository.delete(route);
	}
}

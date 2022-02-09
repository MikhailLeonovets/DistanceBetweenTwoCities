package com.itechart.demo.service.impl.database_service.jdbc_template;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.entity.jdbc_template.impl.RouteJdbcTemplateRepository;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;

import java.util.List;

public class RouteJdbcService implements RouteService {//TODO
	private final RouteJdbcTemplateRepository repository;

	public RouteJdbcService(RouteJdbcTemplateRepository repository) {
		this.repository = repository;
	}

	@Override
	public Route save(Route route) {
		return repository.save(route);
	}

	@Override
	public List<Route> findAll() {
		return repository.getAll();
	}

	@Override
	public Route findById(Long id) throws RouteNotFoundException {
		return repository.getById(id);
	}

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException {
		return repository.findRoutesByFirstCity(firstCity); //todo
	}

	@Override
	public Route update(Route route) {
		return repository.update(route); //todo
	}

	@Override
	public Route findRouteBetweenCities(City firstCity, City secondCity) throws RouteNotFoundException {
		return repository.findRouteBetweenCities(firstCity, secondCity); //todo
	}

	@Override
	public void deleteById(Long id) throws RouteNotFoundException {
		repository.deleteById(id);
	}

	@Override
	public void delete(Route route) {
		repository.delete(route); //todo
	}
}

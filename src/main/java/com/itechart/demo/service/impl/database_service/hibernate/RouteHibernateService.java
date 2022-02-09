package com.itechart.demo.service.impl.database_service.hibernate;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.hibernate.RouteHibernateRepository;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteHibernateService implements RouteService { //TODO
	private final RouteHibernateRepository repository;

	public RouteHibernateService(RouteHibernateRepository repository) {
		this.repository = repository;
	}

	@Override
	public Route save(Route route) {
		return repository.save(route);
		//todo
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
		return repository.findRouteBetweenCities(firstCity, secondCity);
	}

	@Override
	public void deleteById(Long id) throws RouteNotFoundException {
		repository.deleteById(id); //todo
	}

	@Override
	public void delete(Route route) {
		repository.delete(route);
	}
}

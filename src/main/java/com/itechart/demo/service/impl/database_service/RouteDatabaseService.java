package com.itechart.demo.service.impl.database_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.RouteRepository;
import com.itechart.demo.repository.hibernate.RouteHibernateRepository;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("routeServiceImpl")
public class RouteDatabaseService implements RouteService {
	private final RouteRepository routeRepository;

	private RouteHibernateRepository repository = new RouteHibernateRepository();

	public RouteDatabaseService(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	@Override
	@Transactional
	public Route save(Route route) {
		return routeRepository.save(route);
	}

	@Override
	public List<Route> findAll() {
		//return routeRepository.findAll();
		return repository.getAll(); //TODO
	}

	@Override
	public Route findById(Long id) throws RouteNotFoundException {
		Optional<Route> optionalRoute = routeRepository.findById(id);
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}

	@Override
	public List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException {
		List<Route> routes = routeRepository.findRoutesByFirstCity(firstCity);
		if (routes.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return routes;
	}

	@Override
	public Route findRouteBetweenCities(City firstCity, City secondCity)
			throws RouteNotFoundException {
		Optional<Route> optionalRoute
				= routeRepository.findByFirstCityIdAndSecondCityId(firstCity.getId(), secondCity.getId());
		if (optionalRoute.isEmpty()) {
			throw new RouteNotFoundException();
		}
		return optionalRoute.get();
	}

	@Override
	public void deleteById(Long id) {
		routeRepository.deleteById(id);
	}

	@Override
	public void delete(Route route) {
		routeRepository.delete(route);
	}
}

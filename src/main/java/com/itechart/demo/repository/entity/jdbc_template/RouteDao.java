package com.itechart.demo.repository.entity.jdbc_template;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;

import java.util.List;
import java.util.Optional;

public interface RouteDao {

	Route save(Route route);

	List<Route> findAll();

	List<Route> findByFirstCity(City firstCity);

	Optional<Route> findRouteBetweenCities(City firstCity, City secondCity);

	Optional<Route> findById(Long id);

	Route update(Route route);

	void deleteById(Long id);

	void delete(Route route);

}

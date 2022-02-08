package com.itechart.demo.repository.entity.jdbc_template;

import com.itechart.demo.repository.entity.Route;

import java.util.List;

public interface RouteDao {

	void save(Route route);

	Route getById(Long id);

	List<Route> getAll();

	void deleteById(Long id);

}

package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.exception.RouteNotFoundException;

import java.util.List;

public interface RouteService {

	List<Route> findALl();

	List<Route> findRoutesByFirstCity(City firstCity) throws RouteNotFoundException;

	Route findRouteBetweenCities(City firstCity, City secondCity) throws RouteNotFoundException;

}

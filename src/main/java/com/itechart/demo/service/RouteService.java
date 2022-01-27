package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;

import java.util.List;

public interface RouteService {

	List<Route> findRoutesByFirstCity(City firstCity);

}

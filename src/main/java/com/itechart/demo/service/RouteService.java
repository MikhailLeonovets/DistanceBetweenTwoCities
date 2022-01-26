package com.itechart.demo.service;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.model.entity.Route;

import java.util.List;

public interface RouteService {

	List<Route> findRoutesByFirstCity(City firstCity);

}

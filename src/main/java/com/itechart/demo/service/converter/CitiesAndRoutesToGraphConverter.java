package com.itechart.demo.service.converter;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.model.Graph;

import java.util.List;

public interface CitiesAndRoutesToGraphConverter {

	Graph convert(List<City> cities, List<Route> routes);

}

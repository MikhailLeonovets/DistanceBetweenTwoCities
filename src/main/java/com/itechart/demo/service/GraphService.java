package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;

import java.util.HashSet;

public interface GraphService {

	void initGraphCity();

	HashSet<City> getAdjacentCities(City city);

}

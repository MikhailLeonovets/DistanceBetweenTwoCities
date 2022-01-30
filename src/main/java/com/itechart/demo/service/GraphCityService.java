package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;

import java.util.HashSet;

public interface GraphCityService {

	void initGraphCity();

	HashSet<City> getAdjacentCities(City city);

}

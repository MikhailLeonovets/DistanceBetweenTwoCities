package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.exception.CityNotFoundException;

import java.util.HashSet;

public interface GraphCityService {

	HashSet<City> getAdjacentCities(City city) throws CityNotFoundException;

}

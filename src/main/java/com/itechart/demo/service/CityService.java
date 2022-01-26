package com.itechart.demo.service;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.service.exception.CityNotFound;

import java.util.List;

public interface CityService {

	List<City> findAll();

	City findByName(String name) throws CityNotFound;

}

package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.exception.CityNotFoundException;

import java.util.List;

public interface CityService {

	List<City> findAll();

	City findByName(String name) throws CityNotFoundException;

	City findById(Long id) throws CityNotFoundException;

}

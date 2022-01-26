package com.itechart.demo.service;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.service.exception.CityNotFound;

public interface CityService {

	City findByName(String name) throws CityNotFound;

}

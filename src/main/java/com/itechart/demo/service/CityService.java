package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.exception.CityNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CityService {

	City save(City city) throws SQLException;

	List<City> findAll();

	City findByName(String name) throws CityNotFoundException;

	City findById(Long id) throws CityNotFoundException;

	City update(City city);

	void deleteById(Long id) throws CityNotFoundException;

	void delete(City city);

}

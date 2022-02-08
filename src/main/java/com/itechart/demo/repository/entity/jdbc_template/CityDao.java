package com.itechart.demo.repository.entity.jdbc_template;

import com.itechart.demo.repository.entity.City;

import java.util.List;

public interface CityDao {
	void save(City city);

	City getById(Long id);

	List<City> getAll();

	void deleteById(Long id);
}

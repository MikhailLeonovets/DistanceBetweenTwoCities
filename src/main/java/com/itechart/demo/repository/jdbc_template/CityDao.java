package com.itechart.demo.repository.jdbc_template;

import com.itechart.demo.repository.entity.City;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CityDao {
	City save(City city) throws SQLException;

	List<City> findAll();

	Optional<City> findByName(String name);

	Optional<City> findById(Long id);

	City update(City city);

	void deleteById(Long id);

	void delete(City city);
}

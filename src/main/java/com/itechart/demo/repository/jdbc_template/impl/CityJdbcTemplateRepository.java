package com.itechart.demo.repository.jdbc_template.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.jdbc_template.CityDao;
import com.itechart.demo.repository.jdbc_template.DataSourceProvider;
import com.itechart.demo.repository.jdbc_template.mapper.CityMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class CityJdbcTemplateRepository implements CityDao {
	private final JdbcTemplate jdbcTemplate;

	public CityJdbcTemplateRepository(DataSourceProvider dataSourceProvider) {
		this.jdbcTemplate = new JdbcTemplate(dataSourceProvider.getDataSource());
	}

	@Override
	public City save(City city) throws SQLException {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName("city").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", city.getName());
		parameters.put("version", city.getVersion());
		city.setId(Long.parseLong(simpleJdbcInsert.executeAndReturnKey(parameters).toString()));
		return city;
	}

	@Override
	public List<City> findAll() {
		return jdbcTemplate.query("SELECT * FROM city", new CityMapper());
	}

	@Override
	public Optional<City> findByName(String name) {
		List<City> cities = jdbcTemplate.query("SELECT * FROM city WHERE name=?", new CityMapper(), name);
		if (cities.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(cities.get(0));
	}

	@Override
	public Optional<City> findById(Long id) {
		List<City> cities = jdbcTemplate.query("SELECT * FROM city WHERE id=?", new CityMapper(), id);
		if (cities.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(cities.get(0));
	}

	@Override
	public City update(City city) {
		jdbcTemplate.update("UPDATE city SET name=?, version=? WHERE id=?",
				city.getName(), city.getVersion() + 1, city.getId());
		return city;
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update("DELETE FROM city WHERE id=?", id);
	}

	@Override
	public void delete(City city) {
		jdbcTemplate.update("DELETE FROM city WHERE id=?", city.getId());
	}
}

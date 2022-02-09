package com.itechart.demo.repository.entity.jdbc_template.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.entity.jdbc_template.DataSourceProvider;
import com.itechart.demo.repository.entity.jdbc_template.RouteDao;
import com.itechart.demo.repository.entity.jdbc_template.mapper.RouteMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Scope("singleton")
@ApplicationScope
public class RouteJdbcTemplateRepository implements RouteDao {
	private final JdbcTemplate jdbcTemplate;

	public RouteJdbcTemplateRepository(DataSourceProvider dataSourceProvider) {
		this.jdbcTemplate = new JdbcTemplate(dataSourceProvider.getDataSource());
	}

	@Override
	public Route save(Route route) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName("route").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("first_city_id", route.getFirstCity().getId());
		parameters.put("second_city_id", route.getSecondCity().getId());
		parameters.put("distance", route.getDistance());
		parameters.put("version", route.getVersion());
		route.setId((Long) simpleJdbcInsert.executeAndReturnKey(parameters));
		return route;
	}

	@Override
	public List<Route> findAll() {
		return jdbcTemplate.query("SELECT * FROM route", new RouteMapper());
	}

	@Override
	public List<Route> findByFirstCity(City firstCity) {
		return jdbcTemplate.query("SELECT * FROM route WHERE first_city_id=?", new RouteMapper(), firstCity.getId());
	}

	@Override
	public Optional<Route> findRouteBetweenCities(City firstCity, City secondCity) {
		return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM route WHERE first_city_id=? AND second_city_id=?",
				new RouteMapper(), firstCity.getId(), secondCity.getId()));
	}

	@Override
	public Optional<Route> findById(Long id) {
		return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM route WHERE id=?", new RouteMapper(), id));
	}

	@Override
	public Route update(Route route) {
		jdbcTemplate.update("UPDATE route SET first_city_id=?, second_city_id=?, distance=?, version=? " +
						"WHERE id=?", route.getFirstCity().getId(), route.getSecondCity().getId(), route.getDistance(),
				route.getVersion(), route.getId());
		return route;
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update("DELETE FROM route WHERE id=?", id);
	}

	@Override
	public void delete(Route route) {
		jdbcTemplate.update("DELETE FROM route WHERE id=?", route.getId());
	}
}

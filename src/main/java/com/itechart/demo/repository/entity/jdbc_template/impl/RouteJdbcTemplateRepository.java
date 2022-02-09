package com.itechart.demo.repository.entity.jdbc_template.impl;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.entity.jdbc_template.DataSourceProvider;
import com.itechart.demo.repository.entity.jdbc_template.RouteDao;
import com.itechart.demo.repository.entity.jdbc_template.mapper.RouteMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Component
@Scope("singleton")
@ApplicationScope
public class RouteJdbcTemplateRepository implements RouteDao {
	private JdbcTemplate jdbcTemplate;

	public RouteJdbcTemplateRepository(DataSourceProvider dataSourceProvider) {
		this.jdbcTemplate = new JdbcTemplate(dataSourceProvider.getDataSource());
	}

	@Override
	public void save(Route route) {
		jdbcTemplate.update("INSERT INTO route (id, first_city_id, second_city_id, distance, version) " +
						"VALUES (?, ?, ?, ?, ?)",
				route.getId(),
				route.getFirstCity().getId(),
				route.getSecondCity().getId(),
				route.getDistance(),
				route.getVersion());
	}

	@Override
	public Route getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM route WHERE id=?", new RouteMapper(), id);
	}

	@Override
	public List<Route> getAll() {
		return jdbcTemplate.query("SELECT * FROM route", new RouteMapper());
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update("DELETE * FROM route WHERE id=?", id);
	}
}

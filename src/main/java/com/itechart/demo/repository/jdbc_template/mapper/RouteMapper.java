package com.itechart.demo.repository.jdbc_template.mapper;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.repository.jdbc_template.DataSourceProvider;
import com.itechart.demo.repository.jdbc_template.impl.CityJdbcTemplateRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.impl.database_service.jdbc_template.CityJdbcService;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class RouteMapper implements RowMapper<Route> {
	private final CityService cityService = new CityJdbcService(new CityJdbcTemplateRepository(new DataSourceProvider()));

	@SneakyThrows
	@Override
	public Route mapRow(ResultSet rs, int rowNum) {
		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setFirstCity(cityService.findById(rs.getLong("first_city_id")));
		route.setSecondCity(cityService.findById(rs.getLong("second_city_id")));
		route.setDistance(rs.getFloat("distance"));
		route.setVersion(rs.getLong("version"));
		return route;
	}

}

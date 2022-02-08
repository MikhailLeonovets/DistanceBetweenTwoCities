package com.itechart.demo.repository.entity.jdbc_template.mapper;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {
	@Override
	public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setFirstCity(rs.getObject("first_city_id", City.class));
		route.setSecondCity(rs.getObject("second_city_id", City.class));
		route.setDistance(rs.getFloat("distance"));
		route.setVersion(rs.getLong("version"));
		return route;
	}
}

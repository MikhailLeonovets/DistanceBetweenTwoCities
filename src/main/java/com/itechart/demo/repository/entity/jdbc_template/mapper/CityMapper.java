package com.itechart.demo.repository.entity.jdbc_template.mapper;

import com.itechart.demo.repository.entity.City;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper implements RowMapper<City> {
	@Override
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		City city = new City();
		city.setId(rs.getLong("id"));
		city.setName(rs.getString("name"));
		city.setVersion(rs.getLong("version"));
		return city;
	}
}

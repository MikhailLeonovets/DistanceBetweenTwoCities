package com.itechart.demo.repository.entity.jdbc_template.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.jdbc_template.CityDao;
import com.itechart.demo.repository.entity.jdbc_template.DataSourceProvider;
import com.itechart.demo.repository.entity.jdbc_template.mapper.CityMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Component
@Scope("singleton")
@ApplicationScope
public class CityJdbcTemplateRepository implements CityDao {
	private JdbcTemplate jdbcTemplate;

	public CityJdbcTemplateRepository(DataSourceProvider dataSourceProvider) {
		this.jdbcTemplate = new JdbcTemplate(dataSourceProvider.getDataSource());
	}

	@Override
	public void save(City city) {
		jdbcTemplate.update("INSERT INTO city (id, name, version) " +
				"VALUES (?, ?, ?)", city.getId(), city.getName(), city.getVersion());
	}

	@Override
	public City getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM city WHERE id=?", new CityMapper(), id);
	}

	@Override
	public List<City> getAll() {
		return jdbcTemplate.query("SELECT * FROM city", new CityMapper());
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update("DELETE * FROM city WHERE id=?", id);
	}
}

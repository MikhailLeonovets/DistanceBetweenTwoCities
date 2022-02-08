package com.itechart.demo.service.impl.database_service.jdbc_template;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.jdbc_template.impl.CityJdbcTemplateRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;

import java.util.List;

public class CityJdbcService implements CityService {//TODO
	private final CityJdbcTemplateRepository cityJdbcTemplateRepository;

	public CityJdbcService(CityJdbcTemplateRepository cityJdbcTemplateRepository) {
		this.cityJdbcTemplateRepository = cityJdbcTemplateRepository;
	}

	@Override
	public City save(City city) {
		return null;
	}

	@Override
	public List<City> findAll() {
		return null;
	}

	@Override
	public City findByName(String name) throws CityNotFoundException {
		return null;
	}

	@Override
	public City findById(Long id) throws CityNotFoundException {
		return null;
	}

	@Override
	public City update(City city) {
		return null;
	}

	@Override
	public void deleteById(Long id) throws CityNotFoundException {

	}

	@Override
	public void delete(City city) {

	}
}

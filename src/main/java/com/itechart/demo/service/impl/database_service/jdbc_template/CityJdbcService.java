package com.itechart.demo.service.impl.database_service.jdbc_template;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.jdbc_template.impl.CityJdbcTemplateRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityJdbcService implements CityService {//TODO
	private final CityJdbcTemplateRepository repository;

	public CityJdbcService(CityJdbcTemplateRepository cityJdbcTemplateRepository) {
		this.cityJdbcTemplateRepository = cityJdbcTemplateRepository;
	}

	@Override
	public City save(City city) {
		return repository.save(city);
		//todo
	}

	@Override
	public List<City> findAll() {
		return repository.getAll();
	}

	@Override
	public City findByName(String name) throws CityNotFoundException {
		return repository.findByName(name); //todo
	}

	@Override
	public City findById(Long id) throws CityNotFoundException {
		return repository.getById(id);
	}

	@Override
	public City update(City city) {
		return repository.update(city); //todo
	}

	@Override
	public void deleteById(Long id) throws CityNotFoundException {
		repository.deleteById(id);
	}

	@Override
	public void delete(City city) {
		repository.delete(city); //todo
	}
}

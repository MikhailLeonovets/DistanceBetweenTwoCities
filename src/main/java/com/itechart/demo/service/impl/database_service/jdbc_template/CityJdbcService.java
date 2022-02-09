package com.itechart.demo.service.impl.database_service.jdbc_template;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.jdbc_template.impl.CityJdbcTemplateRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CityJdbcService implements CityService {//TODO
	private final CityJdbcTemplateRepository repository;

	public CityJdbcService(CityJdbcTemplateRepository cityJdbcTemplateRepository) {
		this.repository = cityJdbcTemplateRepository;
	}

	@Override
	public City save(City city) throws SQLException {
		return repository.save(city);
	}

	@Override
	public List<City> findAll() {
		return repository.findAll();
	}

	@Override
	public City findByName(String name) throws CityNotFoundException {
		Optional<City> optionalCity = repository.findByName(name);
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}

	@Override
	public City findById(Long id) throws CityNotFoundException {
		Optional<City> optionalCity = repository.findById(id);
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}

	@Override
	public City update(City city) {
		return repository.update(city);
	}

	@Override
	public void deleteById(Long id) throws CityNotFoundException {
		repository.deleteById(id);
	}

	@Override
	public void delete(City city) {
		repository.delete(city);
	}
}

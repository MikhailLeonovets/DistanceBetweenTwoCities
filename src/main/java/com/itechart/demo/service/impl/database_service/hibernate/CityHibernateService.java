package com.itechart.demo.service.impl.database_service.hibernate;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.hibernate.CityHibernateRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityHibernateService implements CityService {
	private final CityHibernateRepository cityHibernateRepository;

	public CityHibernateService(CityHibernateRepository cityHibernateRepository) {
		this.cityHibernateRepository = cityHibernateRepository;
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

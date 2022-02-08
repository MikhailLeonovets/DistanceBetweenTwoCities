package com.itechart.demo.service.impl.database_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.repository.hibernate.CityHibernateRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("cityServiceImpl")
public class CityDatabaseService implements CityService {
	private final CityRepository cityRepository;
	private final CityHibernateRepository cityHibernateRepository = new CityHibernateRepository();

	public CityDatabaseService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	@Transactional
	public City save(City city) {
		//return cityRepository.save(city);
		cityHibernateRepository.save(city);
		return null; //TODO
	}

	@Override
	public List<City> findAll() {
		//return cityRepository.findAll();
		return cityHibernateRepository.getAll(); //TODO
	}

	@Override
	public City findByName(String name) throws CityNotFoundException {
		Optional<City> optionalCity = cityRepository.findByName(name);
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}

	@Override
	public City findById(Long id) throws CityNotFoundException {
		Optional<City> optionalCity = cityRepository.findById(id);
		if (optionalCity.isEmpty()) {
			throw new CityNotFoundException();
		}
		return optionalCity.get();
	}

	@Override
	public void deleteById(Long id) {
		cityRepository.deleteById(id);
	}

	@Override
	public void delete(City city) {
		cityRepository.delete(city);
	}
}

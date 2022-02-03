package com.itechart.demo.service.impl.repository_service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("cityServiceImpl")
public class CityDatabaseService implements CityService {
	private final CityRepository cityRepository;

	public CityDatabaseService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
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
}

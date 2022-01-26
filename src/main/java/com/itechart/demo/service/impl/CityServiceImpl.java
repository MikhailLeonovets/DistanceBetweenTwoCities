package com.itechart.demo.service.impl;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
	private final CityRepository cityRepository;

	@Override
	public City findByName(String name) throws CityNotFound {
		Optional<City> optionalCity = cityRepository.findByName(name);
		if (optionalCity.isEmpty()) {
			throw new CityNotFound();
		}
		return optionalCity.get();
	}
}

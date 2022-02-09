package com.itechart.demo.service.impl.database_service.spring_data;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.spring_data.CityRepository;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("cityServiceImpl")
public class CitySpringDataService implements CityService {
	private final CityRepository cityRepository;

	public CitySpringDataService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	@Transactional
	public City save(City city) {
		return cityRepository.save(city);
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

	@Override
	public City update(City city) {
		return save(city);
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

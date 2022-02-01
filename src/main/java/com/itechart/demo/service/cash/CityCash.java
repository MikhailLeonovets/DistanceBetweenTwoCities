package com.itechart.demo.service.cash;

import com.itechart.demo.repository.CityRepository;
import com.itechart.demo.repository.entity.City;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@Scope("singleton")
public class CityCash {
	private final CityRepository cityRepository;

	private List<City> cities;


	public CityCash(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public void init(){
		this.cities = cityRepository.findAll();
	}
}

package com.itechart.demo.controller;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathService;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Path;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/path-calculator")
public class Controller {
	private final CityService cityService;
	private final PathService pathService;

	public Controller(CityService cityService, PathService pathService) {
		this.cityService = cityService;
		this.pathService = pathService;
	}

	@GetMapping
	public List<City> getCities() {
		return cityService.findAll();
	}

	@GetMapping("/path/{firstCityId}/{secondCityId}")
	public Set<Path> getPaths(@PathVariable Long firstCityId, @PathVariable Long secondCityId)
			throws PathNotFoundException, RouteNotFoundException, CityNotFoundException {
		City firstCity = cityService.findById(firstCityId);
		City secondCity = cityService.findById(secondCityId);
		return pathService.getPaths(firstCity, secondCity);
	}
}

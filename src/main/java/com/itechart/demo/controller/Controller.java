package com.itechart.demo.controller;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathCalculatorService;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Path;
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
	private final PathCalculatorService pathCalculatorService;

	public Controller(CityService cityService, PathCalculatorService pathService) {
		this.cityService = cityService;
		this.pathCalculatorService = pathService;
	}

	@GetMapping
	public List<City> getCities() {
		return cityService.findAll();
	}

	@GetMapping("/path/{firstCityId}/{secondCityId}")
	public Set<Path> getPaths(@PathVariable Long firstCityId, @PathVariable Long secondCityId)
			throws PathNotFoundException, RouteNotFoundException {
		City firstCity = cityService.getById(firstCityId);
		City secondCity = cityService.getById(secondCityId);
		return pathCalculatorService.calculatePaths(firstCity, secondCity);
	}
}

package com.itechart.demo.controller;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.model.payload.request.Cities;
import com.itechart.demo.model.payload.response.PathVariant;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.PathService;
import com.itechart.demo.service.exception.PathNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
	private final CityService cityService;
	private final PathService pathService;

	@GetMapping
	public List<City> getCities() {
		return cityService.findAll();
	}

	@PostMapping
	public PathVariant getPaths(@RequestBody Cities cities) throws PathNotFound {
		City firstCity = cityService.getById(cities.getFirstCityId());
		City secondCity = cityService.getById(cities.getSecondCityId());
		return pathService.getPaths(firstCity, secondCity);
	}
}

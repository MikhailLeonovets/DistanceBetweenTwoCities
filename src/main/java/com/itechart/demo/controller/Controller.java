package com.itechart.demo.controller;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
	private final CityService cityService;

	@GetMapping
	public List<City> getCities() {
		return cityService.findAll();
	}


}

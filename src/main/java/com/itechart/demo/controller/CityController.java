package com.itechart.demo.controller;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@PropertySource("classpath:messages/ru/crud/success.properties")
@RequestMapping("/path-calculator/city")
public class CityController {
	private final CityService cityService;

	@Value("${city.created}")
	private String cityCreated;
	@Value("${city.updated}")
	private String cityUpdated;
	@Value("${city.deleted}")
	private String cityDeleted;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping()
	public ResponseEntity<?> createCity(@RequestBody City city) throws SQLException {
		cityService.save(city);
		return ResponseEntity.ok(new MessageResponse(cityCreated));
	}

	@GetMapping
	public List<City> getCities() {
		return cityService.findAll();
	}

	@GetMapping("/{id}")
	public City getCityById(@PathVariable Long id) throws CityNotFoundException {
		return cityService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody City city) {
		city.setId(id);
		cityService.update(city);
		return ResponseEntity.ok(new MessageResponse(cityUpdated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable Long id) throws CityNotFoundException {
		cityService.deleteById(id);
		return ResponseEntity.ok(new MessageResponse(cityDeleted));
	}
}

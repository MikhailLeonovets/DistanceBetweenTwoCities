package com.itechart.demo.controller;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.CityService;
import com.itechart.demo.service.exception.CityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.itechart.demo.controller.constant.success_msg.CreatedMessageResponse.CITY_CREATED_MSG;
import static com.itechart.demo.controller.constant.success_msg.DeletedMessageResponse.CITY_DELETED_MSG;
import static com.itechart.demo.controller.constant.success_msg.UpdatedMessageResponse.CITY_UPDATED_MSG;

@RestController
@RequestMapping("/path-calculator/city")
public class CityController {
	private final CityService cityService;

	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@PostMapping()
	public ResponseEntity<?> createCity(@RequestBody City city) {
		cityService.save(city);
		return ResponseEntity.ok(new MessageResponse(CITY_CREATED_MSG));
	}

	@GetMapping
	public List<City> getCities() {
		return cityService.findAll();
	}

	@GetMapping("/{id}")
	public City getCityById(@PathVariable Long id) throws CityNotFoundException {
		return cityService.findById(id);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateCity(@PathVariable Long id, @RequestBody City city) {
		city.setId(id);
		cityService.save(city);
		return ResponseEntity.ok(new MessageResponse(CITY_UPDATED_MSG));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCity(@PathVariable Long id) throws CityNotFoundException {
		cityService.deleteById(id);
		return ResponseEntity.ok(new MessageResponse(CITY_DELETED_MSG));
	}


}

package com.itechart.demo.controller;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
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

import java.util.List;

@RestController
@PropertySource("classpath:messages/ru/crud/success.properties")
@RequestMapping("/path-calculator/route")
public class RouteController {
	private final RouteService routeService;

	@Value("${route.created}")
	private String routeCreated;
	@Value("${route.updated}")
	private String routeUpdated;
	@Value("${route.deleted}")
	private String routeDeleted;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@PostMapping
	public ResponseEntity<?> createRoute(@RequestBody Route route) {
		routeService.save(route);
		return ResponseEntity.ok(new MessageResponse(routeCreated));
	}

	@GetMapping
	public List<Route> getRoutes() {
		return routeService.findAll();
	}

	@GetMapping("/{id}")
	public Route getRouteById(@PathVariable Long id) throws RouteNotFoundException {
		return routeService.findById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRoute(@PathVariable Long id,
	                                     @RequestBody Route route) throws RouteNotFoundException {
		route.setId(id);
		routeService.update(route);
		return ResponseEntity.ok(new MessageResponse(routeUpdated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoute(@PathVariable Long id) throws RouteNotFoundException {
		routeService.deleteById(id);
		return ResponseEntity.ok(new MessageResponse(routeDeleted));
	}
}

package com.itechart.demo;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.service.RouteService;
import com.itechart.demo.service.exception.RouteNotFoundException;
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

import static com.itechart.demo.controller.constant.success_msg.CreatedMessageResponse.ROUTE_CREATED_MSG;
import static com.itechart.demo.controller.constant.success_msg.DeletedMessageResponse.ROUTE_DELETED_MSG;
import static com.itechart.demo.controller.constant.success_msg.UpdatedMessageResponse.ROUTE_UPDATED_MSG;

@RestController
@RequestMapping("/path-calculator/route")
public class RouteController {
	private final RouteService routeService;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@PostMapping
	public ResponseEntity<?> createRoute(@RequestBody Route route) {
		routeService.save(route);
		return ResponseEntity.ok(new MessageResponse(ROUTE_CREATED_MSG));
	}

	@GetMapping
	public List<Route> getRoutes() {
		return routeService.findAll();
	}

	@GetMapping("/{id}")
	public Route getRouteById(@PathVariable Long id) throws RouteNotFoundException {
		return routeService.findById(id);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> updateRoute(@PathVariable Long id,
	                                     @RequestBody Route route) {
		route.setId(id);
		routeService.save(route);
		return ResponseEntity.ok(new MessageResponse(ROUTE_UPDATED_MSG));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoute(@PathVariable Long id) throws RouteNotFoundException {
		routeService.deleteById(id);
		return ResponseEntity.ok(new MessageResponse(ROUTE_DELETED_MSG));
	}

}

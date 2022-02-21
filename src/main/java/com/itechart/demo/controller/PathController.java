package com.itechart.demo.controller;

import com.itechart.demo.service.PathService;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.EmptyInputException;
import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Path;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/path-calculator/path")
public class PathController {
	private final PathService pathService;

	public PathController(PathService pathService) {
		this.pathService = pathService;
	}

	@GetMapping("/{firstCityId}/{secondCityId}")
	public Set<Path> getPaths(@PathVariable Long firstCityId, @PathVariable Long secondCityId)
			throws PathNotFoundException, RouteNotFoundException, CityNotFoundException, GraphNullException,
			EmptyInputException {
		return pathService.getPaths(firstCityId, secondCityId);
	}
}

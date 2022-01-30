package com.itechart.demo.service.impl;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.PathService;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Path;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PathServiceImpl implements PathService {
	private final PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService;

	public PathServiceImpl(PathDepthFirstSearchCalculatorService pathDepthFirstSearchCalculatorService) {
		this.pathDepthFirstSearchCalculatorService = pathDepthFirstSearchCalculatorService;
	}

	@Override
	public Set<Path> getPaths(City firstCity, City secondCity) throws PathNotFoundException, RouteNotFoundException {
		return pathDepthFirstSearchCalculatorService.calculatePaths(firstCity, secondCity);
	}
}

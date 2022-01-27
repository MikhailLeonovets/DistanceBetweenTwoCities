package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Path;

import java.util.Set;

public interface PathCalculatorService {

	Set<Path> calculatePaths(City firstCity, City secondCity) throws PathNotFoundException, RouteNotFoundException;

}
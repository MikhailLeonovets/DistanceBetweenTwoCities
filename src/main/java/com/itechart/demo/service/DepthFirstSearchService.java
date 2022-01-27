package com.itechart.demo.service;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;

import java.util.LinkedList;

public interface DepthFirstSearchService {

	void depthFirstSearch(LinkedList<City> visited, City lastCity) throws PathNotFoundException, RouteNotFoundException;

}

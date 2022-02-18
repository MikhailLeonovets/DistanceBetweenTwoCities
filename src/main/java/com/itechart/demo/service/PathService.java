package com.itechart.demo.service;

import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import com.itechart.demo.service.model.Path;

import java.util.Set;

public interface PathService {

	Set<Path> getPaths(Long firstCityId, Long secondCity) throws PathNotFoundException,
			RouteNotFoundException, CityNotFoundException, GraphNullException;

}

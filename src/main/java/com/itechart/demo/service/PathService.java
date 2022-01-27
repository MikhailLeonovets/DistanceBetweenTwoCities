package com.itechart.demo.service;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.model.entity.Path;
import com.itechart.demo.model.payload.response.PathVariant;
import com.itechart.demo.service.exception.PathNotFound;

import java.util.Set;

public interface PathService {

	PathVariant getPaths(City firstCity, City secondCity) throws PathNotFound;

}

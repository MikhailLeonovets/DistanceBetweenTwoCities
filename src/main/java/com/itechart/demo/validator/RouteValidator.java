package com.itechart.demo.validator;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.validator.exception.RouteExistsValidationException;

public interface RouteValidator {

	boolean checkDoesRouteExist(Route route) throws RouteExistsValidationException;

}

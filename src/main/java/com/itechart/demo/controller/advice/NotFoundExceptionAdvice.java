package com.itechart.demo.controller.advice;

import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionAdvice {

	@ExceptionHandler(PathNotFoundException.class)
	public ResponseEntity<?> handlePathNotFoundException(PathNotFoundException e) {
		return ResponseEntity.badRequest().body("Path is not found");
	}

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<?> handleCityNotFoundException(CityNotFoundException e) {
		return ResponseEntity.badRequest().body("City is not found");
	}

	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<?> handleRouteNotFoundException(RouteNotFoundException e) {
		return ResponseEntity.badRequest().body("Route is not found");
	}
}

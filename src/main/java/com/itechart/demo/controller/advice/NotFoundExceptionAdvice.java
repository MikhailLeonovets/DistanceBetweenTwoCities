package com.itechart.demo.controller.advice;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:messages/ru/exception/not_found.properties")
public class NotFoundExceptionAdvice {
	@Value("${path.exception}")
	private String pathNotFoundMsg;
	@Value("${city.exception}")
	private String cityNotFoundMsg;
	@Value("${route.exception}")
	private String routeNotFoundMsg;

	@ExceptionHandler(PathNotFoundException.class)
	public ResponseEntity<?> handlePathNotFoundException(PathNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(pathNotFoundMsg));
	}

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<?> handleCityNotFoundException(CityNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(cityNotFoundMsg));
	}

	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<?> handleRouteNotFoundException(RouteNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(routeNotFoundMsg));
	}
}

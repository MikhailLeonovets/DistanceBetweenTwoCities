package com.itechart.demo.controller.advice;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.service.exception.CityNotFoundException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.exception.RouteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.itechart.demo.controller.constant.exception_msg.ExceptionMessageResponse.*;

@ControllerAdvice
public class NotFoundExceptionAdvice {

	@ExceptionHandler(PathNotFoundException.class)
	public ResponseEntity<?> handlePathNotFoundException(PathNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(PATH_NOT_FOUND_MSG));
	}

	@ExceptionHandler(CityNotFoundException.class)
	public ResponseEntity<?> handleCityNotFoundException(CityNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(CITY_NOT_FOUND_MSG));
	}

	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<?> handleRouteNotFoundException(RouteNotFoundException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(ROUTE_NOT_FOUND_MSG));
	}
}

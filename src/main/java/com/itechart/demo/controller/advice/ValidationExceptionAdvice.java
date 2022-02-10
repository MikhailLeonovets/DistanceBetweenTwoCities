package com.itechart.demo.controller.advice;

import com.itechart.demo.controller.payload.response.MessageResponse;
import com.itechart.demo.validator.exception.RouteExistsValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@PropertySource("classpath:messages/ru/exception/validation.properties")
public class ValidationExceptionAdvice {
	@Value("${route.already.exists}")
	private String routeAlreadyExistsMsg;

	@ExceptionHandler(RouteExistsValidationException.class)
	public ResponseEntity<?> handlePathNotFoundException(RouteExistsValidationException e) {
		return ResponseEntity.badRequest().body(new MessageResponse(routeAlreadyExistsMsg));
	}
}

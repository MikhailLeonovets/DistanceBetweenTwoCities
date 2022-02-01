package com.itechart.demo.controller.advice;

import com.itechart.demo.service.exception.PathNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotFoundExceptionAdvice {

	@ExceptionHandler(PathNotFoundException.class)
	public ResponseEntity<?> handleTaxesNotDefinedException(PathNotFoundException e) {
		return ResponseEntity.badRequest().body("Path is not found");
	}
}

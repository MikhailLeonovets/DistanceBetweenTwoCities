package com.itechart.demo.controller.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@PropertySource("classpath:messages/ru/exception/validation.properties")
public class ValidationExceptionAdvice {
	@Value("${route.already.exists}")
	private String routeAlreadyExistsMsg;
}

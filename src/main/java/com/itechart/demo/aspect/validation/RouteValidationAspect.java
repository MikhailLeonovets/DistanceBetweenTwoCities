package com.itechart.demo.aspect.validation;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.validator.RouteValidator;
import com.itechart.demo.validator.exception.RouteExistsValidationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class RouteValidationAspect {
	private final RouteValidator routeValidator;

	public RouteValidationAspect(RouteValidator routeValidator) {
		this.routeValidator = routeValidator;
	}

	@Pointcut("execution(* com.itechart.demo.service.RouteService.save(..)) && args(..)")
	public void callAtRouteSaveMethod() {
	}

	@Before("callAtRouteSaveMethod()")
	public void beforeCallAtRouteSaveMethod(JoinPoint joinPoint) throws RouteExistsValidationException {
		Route route = (Route) Arrays.stream(joinPoint.getArgs()).findFirst().get();
		routeValidator.checkDoesRouteExist(route);
	}
}

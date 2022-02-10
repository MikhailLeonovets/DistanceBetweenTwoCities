package com.itechart.demo.aspect.validation;

import com.itechart.demo.repository.entity.Route;
import com.itechart.demo.validator.RouteValidator;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

//@Aspect
public class RouteValidationAspect {
	private final RouteValidator routeValidator;

	public RouteValidationAspect(RouteValidator routeValidator) {
		this.routeValidator = routeValidator;
	}

	//@Pointcut("execution(* com.itechart.demo.service.RouteService.save(..)) && args(..)")
	public void callAtRouteSaveMethod() {
	}

	//@Before("callAtRouteSaveMethod()")
	public void beforeCallAtRouteSaveMethod(JoinPoint joinPoint) {
		Route route = (Route) Arrays.stream(joinPoint.getArgs()).findFirst().get();
		routeValidator.checkRouteExists(route);
	}
}

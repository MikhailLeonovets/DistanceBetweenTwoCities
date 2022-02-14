package com.itechart.demo.aspect.logging;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@PropertySource("classpath:messages/en/logging_database.properties")
public class RouteServiceLogging {
	private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceLogging.class);

	@Value("${logging.method.entity.saved}")
	private String entitySavedMsg;
	@Value("${logging.method.entity.updated}")
	private String entityUpdatedMsg;
	@Value("${logging.method.entity.deleted}")
	private String entityDeletedMsg;

	@Pointcut("execution(* com.itechart.demo.service.RouteService.save(..)) && args(..)")
	public void citySave() {
	}

	@Pointcut("execution(* com.itechart.demo.service.RouteService.update(..)) && args(..)")
	public void cityUpdate() {
	}

	@Pointcut("execution(void com.itechart.demo.service.RouteService.deleteById(..)) && args(..))")
	public void cityDelete() {
	}

	@After("citySave()")
	public void afterCitySave(JoinPoint joinPoint) {
		Route route = (Route) Arrays.stream(joinPoint.getArgs()).findFirst().get();
		LOGGER.info(entitySavedMsg + route);
	}

	@After("cityUpdate()")
	public void afterCityUpdate(JoinPoint joinPoint) {
		Route route = (Route) Arrays.stream(joinPoint.getArgs()).findFirst().get();
		LOGGER.info(entityUpdatedMsg + route);
	}

	@After("cityDelete()")
	public void afterCityDelete(JoinPoint joinPoint) {
		Object objectCity = Arrays.stream(joinPoint.getArgs()).findFirst().get();
		LOGGER.info(entityDeletedMsg + objectCity);
	}
}

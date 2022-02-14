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
public class CityServiceLogging {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceLogging.class);

	@Value("${logging.method.entity.saved}")
	private String entitySavedMsg;
	@Value("${logging.method.entity.updated}")
	private String entityUpdatedMsg;
	@Value("${logging.method.entity.deleted}")
	private String entityDeletedMsg;

	@Pointcut("execution(* com.itechart.demo.service.CityService.save(..)) && args(..)")
	public void citySave() {
	}

	@Pointcut("execution(* com.itechart.demo.service.CityService.update(..)) && args(..)")
	public void cityUpdate() {
	}

	@Pointcut("execution(void com.itechart.demo.service.CityService.deleteById(..)) && args(..))")
	public void cityDelete() {
	}

	@After("citySave()")
	public void afterCitySave(JoinPoint joinPoint) {
		City city = (City) Arrays.stream(joinPoint.getArgs()).findFirst().get();
		LOGGER.info(entitySavedMsg + city);
	}

	@After("cityUpdate()")
	public void afterCityUpdate(JoinPoint joinPoint) {
		City city = (City) Arrays.stream(joinPoint.getArgs()).findFirst().get();
		LOGGER.info(entityUpdatedMsg + city);
	}

	@After("cityDelete()")
	public void afterCityDelete(JoinPoint joinPoint) {
		Object objectCity = Arrays.stream(joinPoint.getArgs()).findFirst().get();
		LOGGER.info(entityDeletedMsg + objectCity);
	}
}

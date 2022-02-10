package com.itechart.demo.config.features;

import com.itechart.demo.aspect.validation.RouteValidationAspect;
import com.itechart.demo.validator.RouteValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:config/features.yml")
@Configuration
public class ValidationFeatureSwitcherConfig {
	private final RouteValidator routeValidator;

	public ValidationFeatureSwitcherConfig(RouteValidator routeValidator) {
		this.routeValidator = routeValidator;
	}

	@Bean()
	@ConditionalOnProperty(
			name = "is-validation-enabled",
			havingValue = "true",
			matchIfMissing = true
	)
	public RouteValidationAspect routeValidationAspect() {
		return new RouteValidationAspect(routeValidator);
	}
}

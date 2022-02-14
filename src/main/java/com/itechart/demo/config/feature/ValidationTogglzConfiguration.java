package com.itechart.demo.config.feature;

import com.itechart.demo.togglz.RouteValidationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.user.ServletUserProvider;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class ValidationTogglzConfiguration implements TogglzConfig {
	@Resource
	private DataSource dataSource;

	@Bean
	@Override
	public Class<? extends Feature> getFeatureClass() {
		return RouteValidationFeature.class;
	}

	@Bean
	@Override
	public StateRepository getStateRepository() {
		return new JDBCStateRepository(dataSource);
	}

	@Bean
	@Override
	public UserProvider getUserProvider() {
		return new ServletUserProvider("");
	}
}

package com.itechart.demo.repository.jdbc_template;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.sql.DataSource;

@Component
@Scope("singleton")
@ApplicationScope
public class DataSourceProvider {
	private final DataSource dataSource;

	public DataSourceProvider() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/distance_between_cities");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}

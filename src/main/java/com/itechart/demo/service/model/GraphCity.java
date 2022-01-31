package com.itechart.demo.service.model;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Getter
@Setter
@Component
@Scope("singleton")
@ApplicationScope
public class GraphCity {
	private Map<City, LinkedHashSet<Route>> graph;

	public GraphCity() {
		graph = new HashMap<>();
	}

}

package com.itechart.demo.service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.LinkedHashSet;
import java.util.Map;

@Getter
@Setter
@Component
@Scope("singleton")
@ApplicationScope
public class Graph {
	private Map<String, LinkedHashSet<String>> graph;

	public Graph() {
	}
}

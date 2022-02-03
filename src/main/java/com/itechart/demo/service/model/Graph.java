package com.itechart.demo.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Map;

@Getter
@Setter
public class Graph {
	private Map<String, LinkedHashSet<String>> graph;

	public Graph() {
	}

	public Graph(Map<String, LinkedHashSet<String>> graph) {
		this.graph = graph;
	}
}

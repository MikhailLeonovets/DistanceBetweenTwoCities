package com.itechart.demo.service.impl.repository_service;

import com.itechart.demo.service.model.Graph;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import com.itechart.demo.service.exception.PathNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Service
public class PathDepthFirstSearchCalculatorServiceImpl implements PathDepthFirstSearchCalculatorService {
	private Graph graph;
	private Set<LinkedList<String>> paths;

	public PathDepthFirstSearchCalculatorServiceImpl() {

	}

	@Override
	public Set<LinkedList<String>> calculatePaths(Graph graph, String beginNode, String endNode) {
		this.graph = graph;
		this.paths = new HashSet<>();
		LinkedList<String> visited = new LinkedList<>();
		visited.add(beginNode);
		depthFirstSearch(visited, endNode);
		return paths;
	}

	private void depthFirstSearch(LinkedList<String> visited, String endNode) {
		HashSet<String> adjacentNodes = graph.getGraph().get(visited.getLast());
		for (String node : adjacentNodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(endNode)) {
				visited.add(node);
				savePath(visited);
				visited.removeLast();
				break;
			}
		}
		for (String node : adjacentNodes) {
			if (visited.contains(node) || node.equals(endNode)) {
				continue;
			}
			visited.addLast(node);
			depthFirstSearch(visited, endNode);
			visited.removeLast();
		}
	}

	private void savePath(LinkedList<String> nodes) {
		LinkedList<String> path = new LinkedList<>(nodes);
		paths.add(path);
	}
}

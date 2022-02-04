package com.itechart.demo.service.impl;

import com.itechart.demo.service.model.Graph;
import com.itechart.demo.service.PathDepthFirstSearchCalculatorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Service
public class PathDepthFirstSearchCalculatorServiceImpl implements PathDepthFirstSearchCalculatorService {

	public PathDepthFirstSearchCalculatorServiceImpl() {
	}

	@Override
	public Set<LinkedList<String>> calculatePaths(Graph graph, String beginNode, String endNode) {
		Set<LinkedList<String>> paths = new HashSet<>();
		LinkedList<String> visited = new LinkedList<>();
		visited.add(beginNode);
		depthFirstSearch(graph, visited, endNode, paths);
		return paths;
	}

	private void depthFirstSearch(Graph graph,
	                              LinkedList<String> visited,
	                              String endNode,
	                              Set<LinkedList<String>> paths) {
		HashSet<String> adjacentNodes = graph.getGraph().get(visited.getLast());
		for (String node : adjacentNodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(endNode)) {
				visited.add(node);
				savePath(visited, paths);
				visited.removeLast();
				break;
			}
		}
		for (String node : adjacentNodes) {
			if (visited.contains(node) || node.equals(endNode)) {
				continue;
			}
			visited.addLast(node);
			depthFirstSearch(graph, visited, endNode, paths);
			visited.removeLast();
		}
	}

	private void savePath(LinkedList<String> nodes, Set<LinkedList<String>> paths) {
		LinkedList<String> path = new LinkedList<>(nodes);
		paths.add(path);
	}
}

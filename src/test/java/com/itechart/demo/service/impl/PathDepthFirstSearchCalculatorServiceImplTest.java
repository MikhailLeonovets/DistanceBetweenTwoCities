package com.itechart.demo.service.impl;

import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.model.Graph;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PathDepthFirstSearchCalculatorServiceImplTest {
	private PathDepthFirstSearchCalculatorServiceImpl underTest;

	@BeforeEach
	void setUp() {
		underTest = new PathDepthFirstSearchCalculatorServiceImpl();
	}

	@Test
	void calculatePathsThrowsGraphNullException() {
		Graph graph = null;
		String beginNode = null;
		String endNode = null;
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.calculatePaths(graph, beginNode, endNode))
				.isInstanceOf(GraphNullException.class);
	}

	@Test
	void canCalculatePathsWithTwoConnectedNodes() throws GraphNullException {
		String nodeA = "A";
		String nodeB = "B";

		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>();
		adjacentNodesToA.add(nodeB);
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>();
		adjacentNodesToB.add(nodeA);

		Graph graph = new Graph();
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(nodeA, adjacentNodesToA);
		graphMap.put(nodeB, adjacentNodesToB);
		graph.setGraph(graphMap);

		Set<LinkedList<String>> result = underTest.calculatePaths(graph, nodeA, nodeB); //actual Result todo
		Set<LinkedList<String>> expected = new HashSet<>(); //todo
		LinkedList<String> path1 = new LinkedList<>();
		path1.add(nodeA);
		path1.add(nodeB);
		expected.add(path1);

		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	void calculatePathsReturnsNoPathsBetweenNodes() throws GraphNullException {
		String nodeA = "A";
		String nodeB = "B";
		String nodeC = "C";
		String nodeD = "D";

		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>();
		adjacentNodesToA.add(nodeB);
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>();
		adjacentNodesToB.add(nodeA);

		LinkedHashSet<String> adjacentNodesToC = new LinkedHashSet<>();
		adjacentNodesToC.add(nodeD);
		LinkedHashSet<String> adjacentNodesToD = new LinkedHashSet<>();
		adjacentNodesToD.add(nodeC);


		Graph graph = new Graph();
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(nodeA, adjacentNodesToA);
		graphMap.put(nodeB, adjacentNodesToB);
		graphMap.put(nodeC, adjacentNodesToC);
		graphMap.put(nodeD, adjacentNodesToD);
		graph.setGraph(graphMap);

		Set<LinkedList<String>> result = underTest.calculatePaths(graph, nodeA, nodeD);
		Set<LinkedList<String>> expected = new HashSet<>();

		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	void canCalculatePathsInGraphWithOneNode() throws GraphNullException {
		String nodeA = "A";
		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>();

		Graph graph = new Graph();
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(nodeA, adjacentNodesToA);
		graph.setGraph(graphMap);

		Set<LinkedList<String>> result = underTest.calculatePaths(graph, nodeA, nodeA);
		Set<LinkedList<String>> expected = new HashSet<>();

		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	void canCalculatePathsBetweenTwoNodesWhereOneNodeOutOfGraph() throws GraphNullException {
		String nodeA = "A";
		String nodeB = "B";
		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>();

		Graph graph = new Graph();
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(nodeA, adjacentNodesToA);
		graph.setGraph(graphMap);

		Set<LinkedList<String>> result = underTest.calculatePaths(graph, nodeA, nodeB);
		Set<LinkedList<String>> expected = new HashSet<>();

		Assertions.assertIterableEquals(expected, result);
	}

	@Test
	void canCalculatePathsBetweenTwoNodeAndReturnsTwoDifferentPaths() throws GraphNullException {
		String nodeA = "A";
		String nodeB = "B";
		String nodeC = "C";

		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>();
		adjacentNodesToA.add(nodeB);
		adjacentNodesToA.add(nodeC);
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>();
		adjacentNodesToB.add(nodeA);
		adjacentNodesToB.add(nodeC);
		LinkedHashSet<String> adjacentNodesToC = new LinkedHashSet<>();
		adjacentNodesToC.add(nodeB);
		adjacentNodesToC.add(nodeA);

		Graph graph = new Graph();
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(nodeA, adjacentNodesToA);
		graphMap.put(nodeB, adjacentNodesToB);
		graphMap.put(nodeC, adjacentNodesToC);
		graph.setGraph(graphMap);

		Set<LinkedList<String>> result = underTest.calculatePaths(graph, nodeA, nodeC);
		Set<LinkedList<String>> expected = new HashSet<>();
		LinkedList<String> firstPath = new LinkedList<>();
		firstPath.add(nodeA);
		firstPath.add(nodeB);
		firstPath.add(nodeC);
		LinkedList<String> secondPath = new LinkedList<>();
		secondPath.add(nodeA);
		secondPath.add(nodeC);
		expected.add(firstPath);
		expected.add(secondPath);

		Assertions.assertIterableEquals(expected, result);
	}
}
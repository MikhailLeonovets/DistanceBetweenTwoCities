package com.itechart.demo.service.impl;

import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.model.Graph;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

class PathDepthFirstSearchCalculatorServiceImplTest {
	private PathDepthFirstSearchCalculatorServiceImpl underTest;

	private static final String NODE_A = "A";
	private static final String NODE_B = "B";
	private static final String NODE_C = "C";
	private static final String NODE_D = "D";

	@BeforeEach
	void setUp() {
		underTest = new PathDepthFirstSearchCalculatorServiceImpl();
	}

	@Test
	void testCalculatePathsThrowsGraphNullExceptionBecauseGraphIsNull() {
		Graph graph = null;
		AssertionsForClassTypes.assertThatThrownBy(() -> underTest.calculatePaths(graph, NODE_A, NODE_B))
				.isInstanceOf(GraphNullException.class);
	}

	@Test
	void testCalculatePathsWithTwoConnectedNodes() throws GraphNullException {
		// Given
		Graph graph = getGraphWithTwoConnectedNodes();

		// When
		Set<LinkedList<String>> actualResult = underTest.calculatePaths(graph, NODE_A, NODE_B);

		// Then
		Set<LinkedList<String>> expectedResult = new HashSet<>();
		LinkedList<String> path1 = new LinkedList<>(Arrays.asList(NODE_A, NODE_B));
		expectedResult.add(path1);

		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsReturnsNoPathsBetweenNodes() throws GraphNullException {
		// Given
		Graph graph = getGraphWithTwoIsolatedSubGraphs();

		// When
		Set<LinkedList<String>> actualResult = underTest.calculatePaths(graph, NODE_A, NODE_D);

		// Then
		Set<LinkedList<String>> expectedResult = new HashSet<>();
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsInGraphWithOneNode() throws GraphNullException {
		// Given
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, new LinkedHashSet<>());
		Graph graph = new Graph(graphMap);

		// When
		Set<LinkedList<String>> actualResult = underTest.calculatePaths(graph, NODE_A, NODE_A);

		// Then
		Set<LinkedList<String>> expectedResult = new HashSet<>();
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsBetweenTwoNodesWhereOneNodeOutOfGraph() throws GraphNullException {
		// Given
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, new LinkedHashSet<>());
		Graph graph = new Graph(graphMap);

		// When
		Set<LinkedList<String>> actualResult = underTest.calculatePaths(graph, NODE_A, NODE_B);

		// Then
		Set<LinkedList<String>> expectedResult = new HashSet<>();
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsBetweenTwoNodesAndReturnsTwoDifferentPaths() throws GraphNullException {
		// Given
		Graph graph = getGraphWithTwoPathsFromAToC();
		LinkedList<String> firstPath = new LinkedList<>(Arrays.asList(NODE_A, NODE_B, NODE_C));
		LinkedList<String> secondPath = new LinkedList<>(Arrays.asList(NODE_A, NODE_C));

		// When
		Set<LinkedList<String>> actualResult = underTest.calculatePaths(graph, NODE_A, NODE_C);

		// Then
		Set<LinkedList<String>> expectedResult = new HashSet<>(Arrays.asList(firstPath, secondPath));
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	/**
	 * @return graph where A, B and C nodes are. A is connected to B and C. B is connected to A and C.
	 * C is connected to B and A.
	 */
	private Graph getGraphWithTwoPathsFromAToC() {
		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>(Arrays.asList(NODE_B, NODE_C));
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>(Arrays.asList(NODE_A, NODE_C));
		LinkedHashSet<String> adjacentNodesToC = new LinkedHashSet<>(Arrays.asList(NODE_B, NODE_A));

		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, adjacentNodesToA);
		graphMap.put(NODE_B, adjacentNodesToB);
		graphMap.put(NODE_C, adjacentNodesToC);
		return new Graph(graphMap);
	}

	/**
	 * @return graph where A and B nodes isolated from D and C nodes.
	 */
	private Graph getGraphWithTwoIsolatedSubGraphs() {
		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>(Arrays.asList(NODE_B));
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>(Arrays.asList(NODE_A));
		LinkedHashSet<String> adjacentNodesToC = new LinkedHashSet<>(Arrays.asList(NODE_D));
		LinkedHashSet<String> adjacentNodesToD = new LinkedHashSet<>(Arrays.asList(NODE_C));

		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, adjacentNodesToA);
		graphMap.put(NODE_B, adjacentNodesToB);
		graphMap.put(NODE_C, adjacentNodesToC);
		graphMap.put(NODE_D, adjacentNodesToD);
		return new Graph(graphMap);
	}

	/**
	 * @return graph with A and B connected Nodes.
	 */
	private Graph getGraphWithTwoConnectedNodes() {
		LinkedHashSet<String> adjacentNodesToA = new LinkedHashSet<>(Arrays.asList(NODE_B));
		LinkedHashSet<String> adjacentNodesToB = new LinkedHashSet<>(Arrays.asList(NODE_A));

		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, adjacentNodesToA);
		graphMap.put(NODE_B, adjacentNodesToB);
		return new Graph(graphMap);
	}
}
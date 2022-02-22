package com.itechart.demo.service.impl;

import com.itechart.demo.service.exception.DataInputException;
import com.itechart.demo.service.model.Graph;
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
	private PathDepthFirstSearchCalculatorServiceImpl pathDepthFirstSearchCalculatorService;

	private static final String NODE_A = "A";
	private static final String NODE_B = "B";
	private static final String NODE_C = "C";
	private static final String NODE_D = "D";

	@BeforeEach
	void setUp() {
		pathDepthFirstSearchCalculatorService = new PathDepthFirstSearchCalculatorServiceImpl();
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphAndStartNodeAndEndNodeAreNull() {
		// Given
		Graph graph = null;
		String startNode = null;
		String endNode = null;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphIsNull() {
		// Given
		Graph graph = null;
		String startNode = NODE_A;
		String endNode = NODE_B;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseStartNodeIsNull() {
		// Given
		Graph graph = new Graph();
		String startNode = null;
		String endNode = NODE_B;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseEndNodeIsNull() {
		// Given
		Graph graph = new Graph();
		String startNode = NODE_A;
		String endNode = null;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseStartNodeAndEndNodeAreNull() {
		// Given
		Graph graph = new Graph();
		String startNode = null;
		String endNode = null;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphAndStartNodeAreNull() {
		// Given
		Graph graph = null;
		String startNode = null;
		String endNode = NODE_A;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphAndEndNodeAreNull() {
		// Given
		Graph graph = null;
		String startNode = NODE_A;
		String endNode = null;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphAndStartNodeAndEndNodeAreEmpty() {
		// Given
		Graph graph = new Graph(new HashMap<>());
		String startNode = "";
		String endNode = "";

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphIsEmpty() {
		// Given
		Graph graph = new Graph(new HashMap<>());
		String startNode = NODE_A;
		String endNode = NODE_B;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseStartNodeIsEmpty() {
		// Given
		Graph graph = getGraphWithTwoPathsFromAToC();
		String startNode = "";
		String endNode = NODE_B;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseEndNodeIsEmpty() {
		// Given
		Graph graph = getGraphWithTwoPathsFromAToC();
		String startNode = NODE_A;
		String endNode = "";

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphAndStartNodeAreEmpty() {
		// Given
		Graph graph = new Graph(new HashMap<>());
		String startNode = "";
		String endNode = NODE_A;

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseStartNodeAndEndNodeAreEmpty() {
		// Given
		Graph graph = getGraphWithTwoPathsFromAToC();
		String startNode = "";
		String endNode = "";

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsThrowsDataInputExceptionBecauseGraphAndEndNodeAreEmpty() {
		// Given
		Graph graph = new Graph(new HashMap<>());
		String startNode = NODE_A;
		String endNode = "";

		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pathDepthFirstSearchCalculatorService.calculatePaths(graph, startNode, endNode));
	}

	@Test
	void testCalculatePathsWithTwoConnectedNodes() throws DataInputException {
		// Given
		Graph graph = getGraphWithTwoConnectedNodes();
		Set<LinkedList<String>> expectedResult = new HashSet<>();
		LinkedList<String> path1 = new LinkedList<>(Arrays.asList(NODE_A, NODE_B));
		expectedResult.add(path1);

		// When
		Set<LinkedList<String>> actualResult =
				pathDepthFirstSearchCalculatorService.calculatePaths(graph, NODE_A, NODE_B);

		// Then
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsReturnsNoPathsBetweenNodes() throws DataInputException {
		// Given
		Graph graph = getGraphWithTwoIsolatedSubGraphs();
		Set<LinkedList<String>> expectedResult = new HashSet<>();

		// When
		Set<LinkedList<String>> actualResult =
				pathDepthFirstSearchCalculatorService.calculatePaths(graph, NODE_A, NODE_D);

		// Then
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsInGraphWithOneNode() throws DataInputException {
		// Given
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, new LinkedHashSet<>());
		Graph graph = new Graph(graphMap);
		Set<LinkedList<String>> expectedResult = new HashSet<>();

		// When
		Set<LinkedList<String>> actualResult =
				pathDepthFirstSearchCalculatorService.calculatePaths(graph, NODE_A, NODE_A);

		// Then
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsBetweenTwoNodesWhereOneNodeOutOfGraph() throws DataInputException {
		// Given
		Map<String, LinkedHashSet<String>> graphMap = new HashMap<>();
		graphMap.put(NODE_A, new LinkedHashSet<>());
		Graph graph = new Graph(graphMap);
		Set<LinkedList<String>> expectedResult = new HashSet<>();

		// When
		Set<LinkedList<String>> actualResult =
				pathDepthFirstSearchCalculatorService.calculatePaths(graph, NODE_A, NODE_B);

		// Then
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testCalculatePathsBetweenTwoNodesAndReturnsTwoDifferentPaths() throws DataInputException {
		// Given
		Graph graph = getGraphWithTwoPathsFromAToC();
		LinkedList<String> firstPath = new LinkedList<>(Arrays.asList(NODE_A, NODE_B, NODE_C));
		LinkedList<String> secondPath = new LinkedList<>(Arrays.asList(NODE_A, NODE_C));
		Set<LinkedList<String>> expectedResult = new HashSet<>(Arrays.asList(firstPath, secondPath));

		// When
		Set<LinkedList<String>> actualResult =
				pathDepthFirstSearchCalculatorService.calculatePaths(graph, NODE_A, NODE_C);

		// Then
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
	 * @return graph where A and B nodes are isolated from D and C nodes.
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
	 * @return graph where A and B are connected Nodes.
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
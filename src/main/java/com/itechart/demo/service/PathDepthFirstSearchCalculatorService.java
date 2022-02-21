package com.itechart.demo.service;

import com.itechart.demo.service.exception.EmptyInputException;
import com.itechart.demo.service.exception.GraphNullException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.model.Graph;

import java.util.LinkedList;
import java.util.Set;

public interface PathDepthFirstSearchCalculatorService {

	Set<LinkedList<String>> calculatePaths(Graph graph, String beginNode, String endNode) throws PathNotFoundException, GraphNullException, EmptyInputException;

}

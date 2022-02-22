package com.itechart.demo.service;

import com.itechart.demo.service.exception.DataInputException;
import com.itechart.demo.service.exception.PathNotFoundException;
import com.itechart.demo.service.model.Graph;

import java.util.LinkedList;
import java.util.Set;

public interface PathDepthFirstSearchCalculatorService {

	Set<LinkedList<String>> calculatePaths(Graph graph, String beginNode, String endNode) throws PathNotFoundException,
			DataInputException;

}

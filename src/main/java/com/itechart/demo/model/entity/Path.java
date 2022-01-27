package com.itechart.demo.model.entity;

import lombok.*;

import java.util.Set;

@Data
public class Path {
	private Set<Route> routesToEndPoint;
	private Float totalDistance;
}

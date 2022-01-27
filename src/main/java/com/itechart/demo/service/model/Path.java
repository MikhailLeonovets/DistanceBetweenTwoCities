package com.itechart.demo.service.model;

import com.itechart.demo.repository.entity.Route;
import lombok.Data;


import java.util.Set;

@Data
public class Path {
	private Set<Route> routesToEndPoint;
	private Float totalDistance;
}

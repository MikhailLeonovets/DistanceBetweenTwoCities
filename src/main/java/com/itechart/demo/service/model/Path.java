package com.itechart.demo.service.model;

import com.itechart.demo.repository.entity.Route;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Path {
	private Set<Route> routesToEndPoint;
	private Float totalDistance;
}

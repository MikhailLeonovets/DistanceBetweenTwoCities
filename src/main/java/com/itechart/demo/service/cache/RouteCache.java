package com.itechart.demo.service.cache;

import com.itechart.demo.repository.entity.Route;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Getter
@Setter
@Component
@Scope("singleton")
@ApplicationScope
public class RouteCache {
	private List<Route> routes;
}

package com.itechart.demo.service.cache;

import com.itechart.demo.repository.entity.City;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Setter
@Getter
@Component
@Scope("singleton")
@ApplicationScope
public class CityCache {
	private List<City> cities;
}

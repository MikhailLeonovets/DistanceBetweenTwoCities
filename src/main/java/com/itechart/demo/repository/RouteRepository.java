package com.itechart.demo.repository;

import com.itechart.demo.model.entity.City;
import com.itechart.demo.model.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findRoutesByFirstCity(City firstCity);

}

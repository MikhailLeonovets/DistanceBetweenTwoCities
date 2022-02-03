package com.itechart.demo.repository;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	List<Route> findAll();

	List<Route> findRoutesByFirstCity(City firstCity);

	Optional<Route> findByFirstCityIdAndSecondCityId(Long firstCityId, Long secondCityId);

}

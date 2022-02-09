package com.itechart.demo.repository.hibernate;

import com.itechart.demo.repository.entity.City;
import com.itechart.demo.repository.hibernate.config.HibernateUtil;
import com.itechart.demo.repository.entity.Route;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope("singleton")
@ApplicationScope
public class RouteHibernateRepository {
	public Route save(Route route) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(route);
		session.flush();
		session.close();
		return session.find(Route.class, route);
	}

	public List<Route> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Route> routeCriteriaQuery = criteriaBuilder.createQuery(Route.class);
		Root<Route> routeRoot = routeCriteriaQuery.from(Route.class);
		routeCriteriaQuery.select(routeRoot);
		return session.createQuery(routeCriteriaQuery).getResultList();
	}

	public List<Route> findByFirstCity(City firstCity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Route> criteriaQuery = criteriaBuilder.createQuery(Route.class);
		Root<Route> root = criteriaQuery.from(Route.class);
		criteriaQuery.select(root).where(root.get("first_city_id").in(firstCity.getId()));
		Query result = session.createQuery(criteriaQuery);
		return new ArrayList<>(result.getResultList());
	}

	public Optional<Route> findRouteBetweenCities(City firstCity, City secondCity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Route> criteriaQuery = criteriaBuilder.createQuery(Route.class);
		Root<Route> root = criteriaQuery.from(Route.class);
		criteriaQuery.select(root).where(root.get("first_city_id").in(firstCity.getId()))
				.where(root.get("second_city_id").in(secondCity.getId()));
		Query result = session.createQuery(criteriaQuery);
		return Optional.of((Route) result.getResultList().stream().findFirst().get());
	}

	public Optional<Route> findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return Optional.of(session.get(Route.class, id));
	}

	public Route update(Route route) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(route);
		session.flush();
		session.close();
		return session.find(Route.class, route);
	}

	public void delete(Route route) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(route);
		session.flush();
		session.close();
	}

	public void deleteById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String deleteQuery = "DELETE Route WHERE id = :id";
		Query query = session.createQuery(deleteQuery);
		query.setParameter("id", id);
		query.executeUpdate();
		session.flush();
		session.close();
	}
}

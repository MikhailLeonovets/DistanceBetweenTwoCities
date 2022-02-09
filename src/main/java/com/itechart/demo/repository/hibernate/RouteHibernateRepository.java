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
import javax.swing.text.html.Option;
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
		route.setId((Long) session.save(route));
		session.flush();
		session.close();
		return route;
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
		Query query = session.createQuery("FROM Route WHERE firstCity=:firstCity");
		query.setParameter("firstCity", firstCity);
		return query.getResultList();
	}

	public Optional<Route> findRouteBetweenCities(City firstCity, City secondCity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Route WHERE firstCity=:firstCity AND secondCity=:secondCity");
		query.setParameter("firstCity", firstCity);
		query.setParameter("secondCity", secondCity);
		return Optional.of((Route) query.getSingleResult());
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

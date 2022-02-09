package com.itechart.demo.repository.hibernate;

import com.itechart.demo.repository.hibernate.config.HibernateUtil;
import com.itechart.demo.repository.entity.Route;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Scope("singleton")
@ApplicationScope
public class RouteHibernateRepository {
	public void save(Route route) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(route);
		session.flush();
		session.close();
	}

	public void delete(Route route) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(route);
		session.flush();
		session.close();
	}

	public List<Route> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Route> routeCriteriaQuery = criteriaBuilder.createQuery(Route.class);
		Root<Route> routeRoot = routeCriteriaQuery.from(Route.class);
		routeCriteriaQuery.select(routeRoot);
		return session.createQuery(routeCriteriaQuery).getResultList();
	}

	public Route getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session.get(Route.class, id);
	}
}

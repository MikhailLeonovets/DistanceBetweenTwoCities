package com.itechart.demo.repository.hibernate;

import com.itechart.demo.repository.hibernate.config.HibernateUtil;
import com.itechart.demo.repository.entity.City;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
@Scope("singleton")
@ApplicationScope
public class CityHibernateRepository {

	public City save(City city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(city);
		session.flush();
		session.close();
		return session.find(City.class, city);
	}

	public List<City> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<City> cityCriteriaQuery = criteriaBuilder.createQuery(City.class);
		Root<City> cityRoot = cityCriteriaQuery.from(City.class);
		cityCriteriaQuery.select(cityRoot);
		return session.createQuery(cityCriteriaQuery).getResultList();
	}

	public Optional<City> findById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return Optional.of(session.get(City.class, id));
	}

	public Optional<City> findByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		City city = session.byNaturalId(City.class)
				.using("name", name)
				.load();
		return Optional.of(city);
	}

	public City update(City city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(city);
		session.flush();
		session.close();
		return session.find(City.class, city);
	}

	public void delete(City city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(city);
		session.flush();
		session.close();
	}

	public void deleteById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String deleteQuery = "DELETE City WHERE id = :id";
		Query query = session.createQuery(deleteQuery);
		query.setParameter("id", id);
		query.executeUpdate();
		session.flush();
		session.close();
	}
}

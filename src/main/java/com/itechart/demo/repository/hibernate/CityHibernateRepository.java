package com.itechart.demo.repository.hibernate;

import com.itechart.demo.repository.config.HibernateUtil;
import com.itechart.demo.repository.entity.City;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityHibernateRepository {

	public void save(City city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(city);
		session.flush();
		session.close();
	}

	public void delete(City city) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(city);
		session.flush();
		session.close();
	}

	public List<City> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<City> cityCriteriaQuery = criteriaBuilder.createQuery(City.class);
		Root<City> cityRoot = cityCriteriaQuery.from(City.class);
		cityCriteriaQuery.select(cityRoot);
		return session.createQuery(cityCriteriaQuery).getResultList();
	}

	public City getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session.get(City.class, id);
	}
}

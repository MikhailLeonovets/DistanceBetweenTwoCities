package com.itechart.demo.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itechart.demo.repository.entity.mapped_super_class.Identity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "route")
public class Route extends Identity implements Serializable {
	@ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "first_city_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private City firstCity;

	@ManyToOne(targetEntity = City.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "second_city_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private City secondCity;

	@Column(name = "distance")
	private Float distance;

	@Version
	private Long version;

	public Route() {

	}

	public Route(City firstCity, City secondCity, Float distance) {
		this.firstCity = firstCity;
		this.secondCity = secondCity;
		this.distance = distance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Route route = (Route) o;
		return firstCity.equals(route.firstCity)
				&& secondCity.equals(route.secondCity)
				&& distance.equals(route.distance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstCity, secondCity, distance);
	}
}
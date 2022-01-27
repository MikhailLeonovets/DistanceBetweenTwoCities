package com.itechart.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "route")
public class Route extends Identity {
	@ManyToOne
	@JoinColumn(name = "first_city_id")
	@JsonIgnore
	private City firstCity;

	@ManyToOne
	@JoinColumn(name = "second_city_id")
	@JsonIgnore
	private City secondCity;

	@Column(name = "distance")
	private Float distance;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Route route = (Route) o;
		return getId() != null && Objects.equals(getId(), route.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

package com.itechart.demo.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itechart.demo.repository.entity.mapped_super_class.Identity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Version;
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
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private City firstCity;

	@ManyToOne
	@JoinColumn(name = "second_city_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private City secondCity;

	@Column(name = "distance")
	private Float distance;

	@Version
	private Long version;

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
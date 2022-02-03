package com.itechart.demo.repository.entity;

import com.itechart.demo.repository.entity.mapped_super_class.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "city")
@NoArgsConstructor
public class City extends Identity {
	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		City city = (City) o;
		return getId() != null && Objects.equals(getId(), city.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

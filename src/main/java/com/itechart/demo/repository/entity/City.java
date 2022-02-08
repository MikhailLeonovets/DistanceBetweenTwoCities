package com.itechart.demo.repository.entity;

import com.itechart.demo.repository.entity.mapped_super_class.Identity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
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

	@Version
	private Long version;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		City city = (City) o;
		return name.equals(city.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, version);
	}
}

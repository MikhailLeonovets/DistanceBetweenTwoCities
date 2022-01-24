package com.itechart.demo.model.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "city")
public class City extends Identity {
	@Column(name = "name", nullable = false)
	private String name;
}

package com.itechart.demo.model.entity;

import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "route")
public class Route extends Identity {
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "first_city_id")
	@ToString.Exclude
	private City firstCity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "second_city_id")
	@ToString.Exclude
	private City secondCity;

	@Column(name = "distance")
	private Float distance;
}

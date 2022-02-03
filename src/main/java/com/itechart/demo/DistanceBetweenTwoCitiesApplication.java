package com.itechart.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DistanceBetweenTwoCitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistanceBetweenTwoCitiesApplication.class, args);
	}

}

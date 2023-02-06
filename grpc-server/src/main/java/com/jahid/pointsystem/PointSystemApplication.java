package com.jahid.pointsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PointSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointSystemApplication.class, args);
	}

}

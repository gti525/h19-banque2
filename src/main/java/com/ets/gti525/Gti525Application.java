package com.ets.gti525;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Gti525Application {

	public static void main(String[] args) {
		SpringApplication.run(Gti525Application.class, args);
	}
}

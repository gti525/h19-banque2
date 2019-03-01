package com.ets.gti525;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Description : Main class of application.
 * 
 * Course : GTI525-01
 * Semester : Winter 2019
 * @author Team bank #2
 * @version 1.0
 * @since 11-01-2019
 */
@SpringBootApplication
@EnableScheduling
public class Gti525Application {

	public static void main(String[] args) {
		SpringApplication.run(Gti525Application.class, args);
	}
}

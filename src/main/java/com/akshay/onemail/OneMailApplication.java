package com.akshay.onemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneMailApplication.class, args);
		System.out.println("Bulk HR Email Application Started Successfully!");
	}

}

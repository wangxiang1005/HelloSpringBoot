package com.paladin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@SpringBootApplication
public class HelloApplication {

	public static void main(String[] args) {
		System.out.println("PaladinApplicationClientApplication Run...!");
		SpringApplication.run(HelloApplication.class, args);
	}
}
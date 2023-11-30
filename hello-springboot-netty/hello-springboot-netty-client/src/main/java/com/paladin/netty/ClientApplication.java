package com.paladin.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		System.out.println("ClientApplication Run...!");
		SpringApplication.run(ClientApplication.class, args);
	}
}
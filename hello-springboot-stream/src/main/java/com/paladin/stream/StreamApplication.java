package com.paladin.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
@SpringBootApplication
public class StreamApplication {

    public static void main(String[] args) {
        System.out.println("StreamApplication Run...!");
        SpringApplication.run(StreamApplication.class, args);
    }
}
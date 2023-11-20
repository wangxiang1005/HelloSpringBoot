package com.paladin.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.arch"));

        SpringApplication.run(Application.class, args);
    }

}

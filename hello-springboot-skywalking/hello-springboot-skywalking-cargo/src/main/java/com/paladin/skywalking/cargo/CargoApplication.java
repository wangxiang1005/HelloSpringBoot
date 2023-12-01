package com.paladin.skywalking.cargo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.paladin.skywalking.cargo.mapper")
public class CargoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CargoApplication.class,args);
    }
}
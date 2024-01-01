package com.paladin.guacamelo.ssh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class GuacameloSSHApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuacameloSSHApplication.class, args);
    }
}
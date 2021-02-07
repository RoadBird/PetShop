package com.newgen.serg.pet_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootMainClass extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMainClass.class, args);
    }
}

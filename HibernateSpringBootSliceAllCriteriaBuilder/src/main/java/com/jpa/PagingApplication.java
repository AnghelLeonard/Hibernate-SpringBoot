package com.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.jpa.domain")   
@ComponentScan({
    "com.jpa.controller",
    "com.jpa.service",
    "com.jpa.repository",
    "com.jpa.repository.impl"
})
public class PagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagingApplication.class, args);
    }
}

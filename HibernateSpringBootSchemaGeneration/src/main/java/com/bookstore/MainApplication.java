package com.bookstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("This application uses JPA 2.1 properties for:");
            System.out.println("\t - Drop and create database schema");
            System.out.println("\t - Generate schema scripts, create.ddl and drop.ddl (check application root folder)");
            System.out.println("\t - Populate database with data from data.sql from META-INF folder");
        };
    }
}

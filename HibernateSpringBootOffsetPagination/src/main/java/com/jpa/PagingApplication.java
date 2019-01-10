package com.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.jpa.domain")   
@ComponentScan({
    "com.jpa.controller",
    "com.jpa.service"    
})
@EnableJpaRepositories("com.jpa.repository")
public class PagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagingApplication.class, args);
    }
}

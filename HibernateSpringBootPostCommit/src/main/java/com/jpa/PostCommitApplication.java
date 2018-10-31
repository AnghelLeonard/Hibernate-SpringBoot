package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PostCommitApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PostCommitApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            userService.updateUser();
        };
    }
}

package com.bookstore;

import com.bookstore.service.InsertFirstAuthorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final InsertFirstAuthorService insertFirstAuthorService;

    public MainApplication(InsertFirstAuthorService insertFirstAuthorService) {
        this.insertFirstAuthorService = insertFirstAuthorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
           
            System.out.println("============================================");
            insertFirstAuthorService.insertFirstAuthor();
            System.out.println("============================================");
        };
    }
}

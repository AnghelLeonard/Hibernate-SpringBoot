package com.bookstore;

import com.bookstore.service.BookstoreService;
import com.bookstore.visitor.BookCoverVisitor;
import com.bookstore.visitor.BookFixHiperlinksVisitor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            bookstoreService.persistAuthorWithBooks();
            bookstoreService.applyVisitor(BookCoverVisitor.class);
            bookstoreService.applyVisitor(BookFixHiperlinksVisitor.class);
        };
    }
}

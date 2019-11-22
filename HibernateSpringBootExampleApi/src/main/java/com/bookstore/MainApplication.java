package com.bookstore;

import com.bookstore.entity.Book;
import com.bookstore.service.BookstoreService;
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

            System.out.println("Find a book:");
            
            // this can come via a controller endpoint
            Book book = new Book();
            book.setTitle("Carrie");
            book.setGenre("Horror");
            book.setPrice(23);
            
            boolean foundAnd = bookstoreService.existsBookAnd(book);            
            System.out.println("\nFound (and): " + foundAnd);
            
            boolean foundOr = bookstoreService.existsBookAnd(book);            
            System.out.println("\nFound (or): " + foundOr);
            
            boolean foundIgnorePath = bookstoreService.existsBookIgnorePath(book);            
            System.out.println("\nFound (ignore path): " + foundIgnorePath);
        };
    }
}

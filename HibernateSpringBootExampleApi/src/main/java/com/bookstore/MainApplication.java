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
            book.setIsbn("001-OG");
            book.setAuthor("Olivia Goy");
            book.setPrice(23);
            
            boolean foundAnd = bookstoreService.existsBook1(book);            
            System.out.println("Found (existsBook1): " + foundAnd + "\n");
            
            boolean foundOr = bookstoreService.existsBook2(book);            
            System.out.println("Found (existsBook2): " + foundOr + "\n");
            
            boolean foundIgnorePath = bookstoreService.existsBook3(book);            
            System.out.println("Found (existsBook3): " + foundIgnorePath + "\n");
        };
    }
}

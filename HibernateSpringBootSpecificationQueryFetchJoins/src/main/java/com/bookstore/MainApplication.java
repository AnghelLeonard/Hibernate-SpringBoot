package com.bookstore;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;

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
            
            System.out.println("\nPagination in memory:");
            System.out.println("---------------------");
            Page<Author> authors1 = bookstoreService.fetchViaJoinFetchSpecification(0, 2);            
            authors1.getContent().forEach(a -> System.out.println(a + "   " + a.getBooks()));
            
            System.out.println("\nPagination in database:");
            System.out.println("---------------------");
            Page<Author> authors2 = bookstoreService.fetchViaJoinFetchInIdsSpecification(0, 2);            
            authors2.getContent().forEach(a -> System.out.println(a + "   " + a.getBooks()));           
        };
    }
}

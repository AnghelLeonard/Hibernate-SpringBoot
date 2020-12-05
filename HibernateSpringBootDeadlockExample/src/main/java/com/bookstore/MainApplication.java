package com.bookstore;

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

    // This application produces an exception of type:
    // com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException: 
    // Deadlock found when trying to get lock; try restarting transaction
    
    // However, the database will retry until one of the transaction (A) succeeds
    
    
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n\nDEADLOCK EXAMPLE ...");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.pessimisticWriteUpdate();
        };
    }
}
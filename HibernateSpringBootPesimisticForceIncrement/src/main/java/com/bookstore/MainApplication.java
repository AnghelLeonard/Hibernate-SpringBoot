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

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            
            // For MySQL5Dialect (MyISAM) stoare engine): row-level locking not supported
            // For MySQL5InnoDBDialect (InnoDB storage engine): row-level locking is aquired via FOR UPDATE
            // For MySQL8Dialect (InnoDB storage engine): row-level locking is aquired via FOR UPDATE NOWAIT
            // running the below method will throw: org.hibernate.QueryTimeoutException
            // Caused by: com.mysql.cj.jdbc.exceptions.MySQLTimeoutException
            bookstoreService.addBooksViaTwoTransactionsTestLocking();
            
            // For all dialects (MySQL5Dialect, MySQL5InnoDBDialect, MySQL8Dialect)            
            // running the below method will throw: javax.persistence.OptimisticLockException
            // Caused by: org.hibernate.StaleObjectStateException:
            // bookstoreService.addBooksViaTwoTransactionsTestVersion();
        };
    }
}

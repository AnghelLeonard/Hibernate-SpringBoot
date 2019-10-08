package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.TransactionDefinition;

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
            System.out.println("\n\nPESSIMISTIC_WRITE and UPDATE ...");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.pessimisticWriteUpdate();

            System.out.println("\n\nPESSIMISTIC_WRITE and INSERT ...");
            System.out.println("\n-------------------------REPEATABLE_READ---------------------");
            bookstoreService.pessimisticWriteInsert(TransactionDefinition.ISOLATION_REPEATABLE_READ);

            System.out.println("\n-------------------------READ_COMMITTED----------------------");
            bookstoreService.pessimisticWriteInsert(TransactionDefinition.ISOLATION_READ_COMMITTED);

            System.out.println("\n\nPESSIMISTIC_WRITE and DELETE ...");
            System.out.println("-----------------------------------------------------------------");
            bookstoreService.pessimisticWriteDelete();
        };
    }
}

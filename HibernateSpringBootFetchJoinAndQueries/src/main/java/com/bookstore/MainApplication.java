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
            System.out.println("\n ------------------ N+1 ----------------- \n");
            System.out.println("-----------------displayBooksCausingNPlus1-------------------- \n");

            bookstoreService.displayBooksCausingNPlus1();

            System.out.println("-----------------displayBooksByAgeGt45CausingNPlus1-------------------- \n");

            bookstoreService.displayBooksByAgeGt45CausingNPlus1();

            System.out.println("\n ----------------- LEFT JOIN ----------------- \n");
            System.out.println("-----------------displayBookById-------------------- \n");

            bookstoreService.displayBookById();
            
            System.out.println("-----------------displayBookByIdViaJoinFetch-------------------- \n");

            bookstoreService.displayBookByIdViaJoinFetch();
            
            System.out.println("-----------------displayBookByIdViaEntityGraph-------------------- \n");

            bookstoreService.displayBookByIdViaEntityGraph();

            System.out.println("------------------displayBooksViaEntityGraph------------------- \n");

            bookstoreService.displayBooksViaEntityGraph();

            System.out.println("-------------------displayBooksByAgeGt45ViaEntityGraph------------------ \n");

            bookstoreService.displayBooksByAgeGt45ViaEntityGraph();

            System.out.println("------------------displayBooksViaJoinfetch------------------- \n");

            bookstoreService.displayBooksViaJoinFetch();
        };
    }
}

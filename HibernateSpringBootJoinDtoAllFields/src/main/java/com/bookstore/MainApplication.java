package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    @Autowired
    private BookstoreService bookstoreService;

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            System.out.println("\n\n Fetch authors read-only entities:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsReadOnlyEntities();
            
            System.out.println("\n\n Fetch authors as array of objects");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObject();
            
            System.out.println("\n\n Fetch authors as array of objects by specifying columns");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObjectColumns();
            
            System.out.println("\n\n Fetch authors as array of objects via native query");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObjectNative();
            
            System.out.println("\n\n Fetch authors as array of objects via query builder mechanism");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsArrayOfObjectQueryBuilderMechanism();
            
            System.out.println("\n\n Fetch authors as Spring projection (DTO):");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsDtoClass();

            System.out.println("\n\n Fetch authors as Spring projection (DTO) by specifying columns:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsDtoClassColumns();

            System.out.println("\n\n Fetch authors as Spring projection (DTO) and native query:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorAsDtoClassNative();

            System.out.println("\n\n Fetch authors as Spring projection (DTO) via query builder mechanism:");
            System.out.println("-----------------------------------------------------------------------------");
            bookstoreService.fetchAuthorByGenreAsDtoClassQueryBuilderMechanism();
        };
    }
}

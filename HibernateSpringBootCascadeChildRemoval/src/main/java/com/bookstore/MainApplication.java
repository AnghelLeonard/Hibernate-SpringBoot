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
            // System.out.println("\n\n deleteViaCascadeRemove()");
            // bookstoreService.deleteViaCascadeRemove();
            
            // System.out.println("\n\n deleteViaOrphanRemoval()");
            // bookstoreService.deleteViaOrphanRemoval();
            
            // System.out.println("\n\n deleteViaIdentifiers()");
            // bookstoreService.deleteViaIdentifiers();
            
            // System.out.println("\n\n deleteViaIdentifiersX()");
            // bookstoreService.deleteViaIdentifiersX();                                    
                        
             System.out.println("\n\n deleteViaBulkIn()");
             bookstoreService.deleteViaBulkIn();
             
             // System.out.println("\n\n deleteViaBulkInX()");
             // bookstoreService.deleteViaBulkInX();
            
             // System.out.println("\n\n deleteViaHardCodedIdentifiers()");
             // bookstoreService.deleteViaHardCodedIdentifiers();
             
             // System.out.println("\n\n deleteViaBulkHardCodedIdentifiers()");
             // bookstoreService.deleteViaBulkHardCodedIdentifiers();
        };
    }
}

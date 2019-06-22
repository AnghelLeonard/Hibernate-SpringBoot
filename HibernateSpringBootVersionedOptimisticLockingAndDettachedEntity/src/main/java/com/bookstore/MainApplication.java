package com.bookstore;

import com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@SpringBootApplication
public class MainApplication {

    private final InventoryService inventoryService;

    public MainApplication(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public OptimisticConcurrencyControlAspect optimisticConcurrencyControlAspect() {
        return new com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect();
    }
    
    @Bean
    public ApplicationRunner init() {
        return args -> {

            Inventory firstInventory = inventoryService.firstTransactionFetchAndReturn();
            
            inventoryService.secondTransactionFetchAndUpdate();
            
            firstInventory.setQuantity(firstInventory.getQuantity() - 1);
            inventoryService.thirdTransactionIsReattachingAndResultsInOLE(firstInventory);
        };
    }
}

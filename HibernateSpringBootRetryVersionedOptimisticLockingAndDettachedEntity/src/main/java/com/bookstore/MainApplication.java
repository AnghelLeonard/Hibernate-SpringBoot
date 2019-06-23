package com.bookstore;

import com.bookstore.service.InventoryService;
import com.bookstore.entity.Inventory;
import com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MainApplication {

    private final InventoryService inventoryService;

    public MainApplication(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public OptimisticConcurrencyControlAspect
            optimisticConcurrencyControlAspect() {

        return new OptimisticConcurrencyControlAspect();
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("Triggering the first transaction ...");
            Inventory firstInventory = inventoryService.firstTransactionFetchesAndReturn();
            System.out.println("First transaction committed successfully ...");

            System.out.println("Triggering the second transaction ...");
            inventoryService.secondTransactionFetchesAndReturn();
            System.out.println("Second transaction committed successfully ...");

            // AT THIS POINT, THE firstInventory IS DETACHED
            firstInventory.setQuantity(firstInventory.getQuantity() - 2);

            System.out.println("Triggering the third transaction ...");
            inventoryService.thirdTransactionMergesAndUpdates(firstInventory);
            System.out.println("Third transaction committed successfully ...");
        };
    }
}

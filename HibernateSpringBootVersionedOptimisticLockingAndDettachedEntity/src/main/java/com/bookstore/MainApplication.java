package com.bookstore;

import com.bookstore.service.InventoryService;
import com.bookstore.entity.Inventory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    public ApplicationRunner init() {
        return args -> {

            Inventory firstInventory = inventoryService.firstTransactionFetchAndReturn();

            // AT THIS POINT, THE firstInventory IS DETACHED
            firstInventory.setQuantity(firstInventory.getQuantity() - 1);
            inventoryService.secondTransactionMergesInventory(firstInventory);
        };
    }
}

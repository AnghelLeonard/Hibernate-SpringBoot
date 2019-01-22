package com.jpa;

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
public class RetryOptimisticLockApplication {

    private final InventoryService inventoryService;

    public RetryOptimisticLockApplication(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RetryOptimisticLockApplication.class, args);
    }

    @Bean
    public OptimisticConcurrencyControlAspect optimisticConcurrencyControlAspect() {
        return new com.vladmihalcea.concurrent.aop.OptimisticConcurrencyControlAspect();
    }
    
    @Bean
    public ApplicationRunner init() {
        return args -> {

            inventoryService.forceOptimisticLockException();
        };
    }
}

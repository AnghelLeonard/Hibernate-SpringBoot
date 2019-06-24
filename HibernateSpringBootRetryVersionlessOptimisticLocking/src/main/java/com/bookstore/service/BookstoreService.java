package com.bookstore.service;

import com.vladmihalcea.concurrent.Retry;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService implements Runnable {
    
    private final InventoryService inventoryService;

    public BookstoreService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
     
    
    @Override
    @Retry(times = 10, on = OptimisticLockingFailureException.class)    
    public void run() {
        inventoryService.updateQuantity();
    }
}

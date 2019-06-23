package com.bookstore.service;

import com.bookstore.entity.Inventory;
import com.bookstore.repository.InventoryRepository;
import com.vladmihalcea.concurrent.Retry;
import javax.transaction.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory firstTransactionFetchesAndReturn() {
        Inventory firstInventory = inventoryRepository.findById(1L).orElseThrow();

        return firstInventory;
    }

    @Transactional
    public void secondTransactionFetchesAndReturn() {
        Inventory secondInventory = inventoryRepository.findById(1L).orElseThrow();

        secondInventory.setQuantity(secondInventory.getQuantity() - 1);
    }

    @Retry(times = 10, on = OptimisticLockingFailureException.class)
    public void thirdTransactionMergesAndUpdates(Inventory firstInventory) {
        try {
            inventoryRepository.save(firstInventory); // calls EntityManager#merge() behind the scene    
        } catch (OptimisticLockingFailureException e) {
            firstInventory.setVersion(
                    inventoryRepository.findById(
                            firstInventory.getId()).orElseThrow().getVersion());
            
            throw e;
        }       
    }
}

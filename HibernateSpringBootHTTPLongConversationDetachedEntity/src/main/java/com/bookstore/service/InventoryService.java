package com.bookstore.service;

import com.bookstore.entity.Inventory;
import com.bookstore.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory fetchInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow();

        return inventory;
    }       
    
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}

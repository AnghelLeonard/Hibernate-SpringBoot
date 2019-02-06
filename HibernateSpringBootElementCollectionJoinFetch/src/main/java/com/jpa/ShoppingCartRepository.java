package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {  
    
    @Query(value = "SELECT p FROM ShoppingCart p JOIN FETCH p.products")
    ShoppingCart fetchShoppingCart();
    
    @Query(value = "SELECT p FROM ShoppingCart p JOIN FETCH p.products q WHERE q.price > ?1")
    ShoppingCart fetchShoppingCartByPrice(int price);
}


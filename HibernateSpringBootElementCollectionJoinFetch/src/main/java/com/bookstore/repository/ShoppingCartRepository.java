package com.bookstore.repository;

import com.bookstore.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {  
    
    @Query(value = "SELECT p FROM ShoppingCart p JOIN FETCH p.books")
    ShoppingCart fetchShoppingCart();
    
    @Query(value = "SELECT p FROM ShoppingCart p JOIN FETCH p.books b WHERE b.price > ?1")
    ShoppingCart fetchShoppingCartByPrice(int price);
}


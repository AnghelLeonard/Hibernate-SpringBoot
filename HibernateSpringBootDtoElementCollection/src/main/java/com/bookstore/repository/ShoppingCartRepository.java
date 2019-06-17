package com.bookstore.repository;

import com.bookstore.entity.ShoppingCart;
import com.bookstore.dto.ShoppingCartDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {  
    
    @Query(value = "SELECT a.owner AS owner, b.title AS title, b.price AS price "
            + "FROM ShoppingCart a JOIN a.books b")
    List<ShoppingCartDto> fetchShoppingCart();
    
    @Query(value = "SELECT a.owner AS owner, b.title AS title, b.price AS price "
            + "FROM ShoppingCart a JOIN a.books b WHERE b.price > ?1")
    List<ShoppingCartDto> fetchShoppingCartByPrice(int price);
}


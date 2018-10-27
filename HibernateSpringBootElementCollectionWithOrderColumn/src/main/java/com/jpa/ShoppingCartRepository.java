package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {  
    
    @Transactional(readOnly=true)
    ShoppingCart findByName(String name);
}


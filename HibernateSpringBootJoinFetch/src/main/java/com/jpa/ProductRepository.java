package com.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {  
    
    @Query(value = "SELECT p FROM Product p JOIN FETCH p.category")
    List<Product> productsIncludingCategory();
}

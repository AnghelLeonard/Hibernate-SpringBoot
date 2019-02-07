package com.jpa;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository<T, ID> extends NaturalRepository<Product, Long>{    
}

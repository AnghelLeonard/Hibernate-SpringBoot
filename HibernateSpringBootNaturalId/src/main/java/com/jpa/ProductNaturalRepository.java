package com.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class ProductNaturalRepository extends NaturalRepositoryImpl<Product, String> {        

    public ProductNaturalRepository() {
        super(Product.class);
    }
}

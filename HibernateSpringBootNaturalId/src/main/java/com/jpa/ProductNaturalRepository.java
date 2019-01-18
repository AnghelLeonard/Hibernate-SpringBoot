package com.jpa;

import org.springframework.stereotype.Repository;

@Repository
// for multiple @NaturalId in the `Product` entity use, `...extends NaturalRepositoryImpl<Product, Serializable>`
public class ProductNaturalRepository extends NaturalRepositoryImpl<Product, String> { 

    public ProductNaturalRepository() {
        super(Product.class);
    }
}

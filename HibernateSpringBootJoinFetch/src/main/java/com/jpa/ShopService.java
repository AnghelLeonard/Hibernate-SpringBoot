package com.jpa;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    private static final Logger logger
            = Logger.getLogger(ShopService.class.getName());

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ShopService(CategoryRepository categoryRepository,
            ProductRepository productRepository) {

        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }
    
    public Category fetchCategoryIncludingProducts(String name){
        
        return categoryRepository.categoryIncludingProducts(name);
    }
    
    public List<Product> fetchProductsIncludingCategory() {
        
        return productRepository.productsIncludingCategory();
    }
}

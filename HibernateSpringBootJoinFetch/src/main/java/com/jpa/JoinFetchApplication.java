package com.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JoinFetchApplication {

    @Autowired
    private ShopService shopService;

    public static void main(String[] args) {
        SpringApplication.run(JoinFetchApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            
            System.out.println("-------------------------------------------------");
            System.out.println("Fetch all categories including products");
            System.out.println("-------------------------------------------------");
                       
            Category category = shopService.fetchCategoryIncludingProducts("Sport");
            
            // this will not trigger LazyInitializationException since the
            // products have been loaded already via JOIN FETCH
            category.getProducts().forEach((t) -> System.out.println("Category: " 
                    + t.getCategory().getCname() + ", Product name: " + t.getPname()));
            
            System.out.println("-------------------------------------------------");
            System.out.println("Fetch all products including categories");
            System.out.println("-------------------------------------------------");
            
            List<Product> products = shopService.fetchProductsIncludingCategory();
            
            // this will not trigger LazyInitializationException since the
            // categories have been loaded already via JOIN FETCH
            products.forEach((t) -> System.out.println("Product name: " 
                    + t.getPname() + ", Category: " + t.getCategory().getCname()));
        };
    }
}

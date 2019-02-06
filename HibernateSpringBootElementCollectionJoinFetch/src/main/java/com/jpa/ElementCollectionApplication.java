package com.jpa;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ElementCollectionApplication {
    
    @Autowired
    ShoppingCartService shoppingCartService;

    private static final Logger logger
            = Logger.getLogger(ElementCollectionApplication.class.getName());
    
    public static void main(String[] args) {
        SpringApplication.run(ElementCollectionApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            logger.info(() -> "Creating the shopping cart with 3 items ...");
            shoppingCartService.createShoppingCartWithProducts();
            
            logger.info(() -> "Getting the shopping cart with 3 items ...");
            ShoppingCart allcart = shoppingCartService.allShoppingCart();
            
            logger.info(() -> "Cart: " + allcart.getProducts());
            
            logger.info(() -> "Getting the shopping cart with products price 90 ...");
            ShoppingCart cart = shoppingCartService.byPriceShoppingCart();
            
            logger.info(() -> "Cart: " + cart.getProducts());
        };
    }
}

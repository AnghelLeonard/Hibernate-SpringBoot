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
            
            logger.info(() -> "Add one item to the existing cart at the beginning ...");
            shoppingCartService.addAtBeginInShoppingCart();
            
            logger.info(() -> "Add one item to the existing cart at the end ...");
            shoppingCartService.addAtEndInShoppingCart();
            
            logger.info(() -> "Add one item to the existing cart in the middle ...");
            shoppingCartService.addAtMiddleInShoppingCart();
            
            logger.info(() -> "Remove first item from the cart ...");
            shoppingCartService.removeFirstItemShoppingCart();
            
            logger.info(() -> "Remove last item from the cart ...");
            shoppingCartService.removeLastItemShoppingCart();
            
            logger.info(() -> "Remove middle item from the cart ...");
            shoppingCartService.removeMiddleItemShoppingCart();
            
        };
    }
}

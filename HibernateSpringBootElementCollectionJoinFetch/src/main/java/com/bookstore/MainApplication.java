package com.bookstore;

import com.bookstore.service.ShoppingCartService;
import com.bookstore.entity.ShoppingCart;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final ShoppingCartService shoppingCartService;

    public MainApplication(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("Fetch shopping cart ...");
            ShoppingCart sc1 = shoppingCartService.allShoppingCart();
            System.out.println("\nOwner: " + sc1.getOwner() + " Books: " + sc1.getBooks());

            System.out.println("\nFetch shopping cart by price ...");
            ShoppingCart sc2 = shoppingCartService.byPriceShoppingCart();
            System.out.println("\nOwner: " + sc2.getOwner() + " Books: " + sc2.getBooks());
        };
    }
}

package com.bookstore;

import com.bookstore.service.ShoppingCartService;
import com.bookstore.dto.ShoppingCartDto;
import java.util.List;
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
            List<ShoppingCartDto> sc1 = shoppingCartService.allShoppingCart();
            sc1.forEach(a -> {
                System.out.println("\nOwner: " + a.getOwner()
                        + " Title: " + a.getTitle() + " Price: " + a.getPrice());
            });
            
            System.out.println("\nFetch shopping cart by price ...");
            List<ShoppingCartDto> sc2 = shoppingCartService.byPriceShoppingCart();
            sc2.forEach(a -> {
                System.out.println("\nOwner: " + a.getOwner()
                        + " Title: " + a.getTitle() + " Price: " + a.getPrice());
            });
        };
    }
}

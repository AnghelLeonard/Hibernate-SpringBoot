package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void createShoppingCartWithProducts() {

        Product tshirt = new Product();
        tshirt.setName("T-Shirt");
        tshirt.setPrice(100);
        
        Product hat = new Product();
        hat.setName("Hat");
        hat.setPrice(75);
        
        Product gloves = new Product();
        gloves.setName("Gloves");
        gloves.setPrice(120);
        
        ShoppingCart cart = new ShoppingCart();
        cart.setName("cart");
        cart.getProducts().add(tshirt);
        cart.getProducts().add(hat);
        cart.getProducts().add(gloves);

        shoppingCartRepository.save(cart);
    }
    
    @Transactional(readOnly=true)
    public ShoppingCart allShoppingCart() {
        
        return shoppingCartRepository.fetchShoppingCart();
    }
    
    @Transactional(readOnly=true)
    public ShoppingCart byPriceShoppingCart() {
        
        return shoppingCartRepository.fetchShoppingCartByPrice(90);
    }
}

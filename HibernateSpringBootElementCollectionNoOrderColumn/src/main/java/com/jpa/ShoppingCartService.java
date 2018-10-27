package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void createShoppingCart() {

        ShoppingCart cart = new ShoppingCart();
        cart.setName("cart");
        cart.getProducts().add("t-shirt");
        cart.getProducts().add("hat");
        cart.getProducts().add("gloves");

        shoppingCartRepository.save(cart);
    }
    
    @Transactional
    public void addInShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().add("clock");
    }
    
    @Transactional
    public void removeFromShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().remove(0);
    }
}

package com.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void createShoppingCartWithProducts() {

        ShoppingCart cart = new ShoppingCart();
        cart.setName("cart");
        cart.getProducts().add("t-shirt");
        cart.getProducts().add("hat");
        cart.getProducts().add("gloves");

        shoppingCartRepository.save(cart);
    }
    
    @Transactional
    public void addAtBeginInShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().add(0, "desk");
    }       
    
    @Transactional
    public void addAtEndInShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().add("clock");
    }
    
    @Transactional
    public void addAtMiddleInShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().add(cart.getProducts().size()/2, "scarf");
    }
            
    @Transactional
    public void removeFirstItemShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().remove(0);
    }
    
    @Transactional
    public void removeLastItemShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().remove(cart.getProducts().size() - 1);
    }
    
    @Transactional
    public void removeMiddleItemShoppingCart() {
        ShoppingCart cart = shoppingCartRepository.findByName("cart");
        
        cart.getProducts().remove(cart.getProducts().size()/2);
    }
}

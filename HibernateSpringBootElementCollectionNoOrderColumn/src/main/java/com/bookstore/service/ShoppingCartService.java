package com.bookstore.service;

import com.bookstore.entity.ShoppingCart;
import com.bookstore.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Transactional
    public void addToTheBeginning() {
        ShoppingCart cart = shoppingCartRepository.findByOwner("Mark Juno");

        cart.getBooks().add(0, "Modern history");
    }

    @Transactional
    public void addToTheEnd() {
        ShoppingCart cart = shoppingCartRepository.findByOwner("Mark Juno");

        cart.getBooks().add("The last day");
    }

    @Transactional
    public void addInTheMiddle() {
        ShoppingCart cart = shoppingCartRepository.findByOwner("Mark Juno");

        cart.getBooks().add(cart.getBooks().size() / 2, "Middle man");
    }

    @Transactional
    public void removeFirst() {
        ShoppingCart cart = shoppingCartRepository.findByOwner("Mark Juno");

        cart.getBooks().remove(0);
    }

    @Transactional
    public void removeLast() {
        ShoppingCart cart = shoppingCartRepository.findByOwner("Mark Juno");

        cart.getBooks().remove(cart.getBooks().size() - 1);
    }

    @Transactional
    public void removeMiddle() {
        ShoppingCart cart = shoppingCartRepository.findByOwner("Mark Juno");

        cart.getBooks().remove(cart.getBooks().size() / 2);
    }
}

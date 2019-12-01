package com.bookstore.strategy;

import com.bookstore.entity.Book;

public interface Delivery<T extends Book> {

    Class<? extends Book> ofBook();
    
    void deliver(T book);
}

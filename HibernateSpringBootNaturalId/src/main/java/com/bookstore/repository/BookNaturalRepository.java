package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.naturalid.NaturalRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
// for multiple @NaturalId in the `Book` entity use, 
// `...extends NaturalRepositoryImpl<Book, Serializable>`
public class BookNaturalRepository extends NaturalRepositoryImpl<Book, String> {

    public BookNaturalRepository() {
        super(Book.class);
    }
}

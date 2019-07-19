package com.bookstore.repository;

import com.bookstore.entity.Book;
import com.bookstore.naturalid.NaturalRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository<T, ID> extends NaturalRepository<Book, Long> {
}

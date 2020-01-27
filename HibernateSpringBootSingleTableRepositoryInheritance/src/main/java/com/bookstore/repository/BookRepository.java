package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BookBaseRepository<Book> {
}

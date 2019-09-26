package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository<T, ID> extends JpaRepository<Book, Long> {
    
    public Book findByTitle(String title);
}

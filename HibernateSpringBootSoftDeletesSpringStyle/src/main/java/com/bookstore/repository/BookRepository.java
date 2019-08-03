package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends SoftDeleteRepository<Book, Long> {
    
    Book findByTitle(String title);
}

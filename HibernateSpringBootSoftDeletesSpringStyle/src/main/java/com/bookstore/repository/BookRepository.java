package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends SoftDeleteRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b WHERE b.title=?1 AND b.deleted=false")
    Book fetchByTitle(String title);
}

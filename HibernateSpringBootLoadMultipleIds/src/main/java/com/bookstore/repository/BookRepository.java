package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository<T, ID> extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b WHERE b.id IN ?1")
    List<Book> fetchByMultipleIds(List<Long> ids);
}

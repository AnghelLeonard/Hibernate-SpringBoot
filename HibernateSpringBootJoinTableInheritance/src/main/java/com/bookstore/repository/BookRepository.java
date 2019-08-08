package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    Book findByTitle(String title);
    
    @Transactional(readOnly = true)
    @Query("SELECT b FROM Book b WHERE b.author.id = ?1")        
    List<Book> fetchBooksByAuthorId(Long authorId);
}

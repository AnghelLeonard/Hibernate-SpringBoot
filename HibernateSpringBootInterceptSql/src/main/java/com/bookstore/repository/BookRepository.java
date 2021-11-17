package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT b FROM Book b JOIN FETCH b.author WHERE b.isbn = ?1")
    // or, via JOIN            
    //@Query(value = "SELECT b, a FROM Book b JOIN b.author a WHERE b.isbn = ?1")
    Book fetchBookWithAuthorByIsbn(String isbn);
}

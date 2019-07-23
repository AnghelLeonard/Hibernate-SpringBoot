package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Query(value="SELECT a FROM Author a JOIN FETCH a.books WHERE a.name = ?1")
    Author authorAndBooks(String name);
}

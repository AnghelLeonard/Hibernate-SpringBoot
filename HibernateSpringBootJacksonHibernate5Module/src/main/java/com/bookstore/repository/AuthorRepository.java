package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    Author findByName(String name);
    
    @Transactional(readOnly = true)
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name=?1")
    Author findByNameWithBooks(String name);
}

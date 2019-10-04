package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {        
    
    public Author findByName(String name);
    
    @Query("SELECT a FROM Author a "
            + "WHERE function('JSON_EXTRACT', a.book, '$.isbn') = ?1")
    public Author findByBookIsbn(String isbn);
    
    @Query(value = "SELECT a.* FROM author a WHERE JSON_EXTRACT(a.book, '$.isbn') = ?1",
            nativeQuery = true)
    public Author findByBookIsbnNativeQuery(String isbn);
}


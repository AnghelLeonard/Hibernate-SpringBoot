package com.bookstore.repository;

import com.bookstore.entity.Author;
import com.bookstore.entity.AuthorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, AuthorId> {   
    
    @Query("SELECT a FROM Author a "
            + "JOIN FETCH a.books WHERE a.id = ?1")
    public Author fetchWithBooks(AuthorId id);
    
    @Query("SELECT a FROM Author a WHERE a.id.name = ?1")
    public Author fetchByName(String name);

}

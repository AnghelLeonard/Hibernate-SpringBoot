package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Transactional(readOnly=true)
    Author findByName(String name);
    
    @Transactional(readOnly=true)
    List<Author> findByAge(int age);
    
    @Transactional(readOnly=true)
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name=?1")        
    Author findByNameWithBooks(String name);
        
    @Transactional    
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Author a WHERE a.id=?1")
    public int deleteByIdentifier(Long id);        
}

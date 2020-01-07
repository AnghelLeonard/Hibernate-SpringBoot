package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
        
    Author findByName(String name);
        
    List<Author> findByAge(int age);
        
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name=?1")        
    Author findByNameWithBooks(String name);
        
    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.genre=?1")        
    List<Author> findByGenreWithBooks(String genre);
            
    @Transactional    
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Author a WHERE a.id=?1")
    public int deleteByIdentifier(Long id);      
    
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM Author a WHERE a.id IN ?1")
    public int deleteBulkByIdentifier(List<Long> id);
}

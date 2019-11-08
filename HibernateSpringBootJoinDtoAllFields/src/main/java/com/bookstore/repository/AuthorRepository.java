package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {       
    
    @Query("SELECT a FROM Author a")
    List<Object[]> fetchAsArray();
    
    @Query("SELECT a.id AS id, a.age AS age, a.name AS name, a.genre AS genre FROM Author a")
    List<Object[]> fetchAsArrayColumns();
    
    @Query(value = "SELECT id, age, name, genre FROM author", nativeQuery = true)
    List<Object[]> fetchAsArrayNative();
        
    List<Object[]> findFirstBy();

    @Query("SELECT a FROM Author a")
    List<AuthorDto> fetchAsDto();
       
    @Query("SELECT a.id AS id, a.age AS age, a.name AS name, a.genre AS genre FROM Author a")
    List<AuthorDto> fetchAsDtoColumns();

    @Query(value = "SELECT id, age, name, genre FROM author", nativeQuery = true)
    List<AuthorDto> fetchAsDtoNative();
    
    List<AuthorDto> findBy();
}

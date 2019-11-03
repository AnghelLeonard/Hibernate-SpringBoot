package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
        
    @Query("SELECT a.age AS age, a.name AS name, a.genre AS genre FROM Author a")
    List<Map<String, Object>> fetchAgeNameGenre();
    
    @Query("SELECT a.name AS name, a.email AS email FROM Author a")
    List<Map<String, Object>> fetchNameEmail();
}

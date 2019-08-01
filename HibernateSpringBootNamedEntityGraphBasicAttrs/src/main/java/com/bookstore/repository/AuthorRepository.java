package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @EntityGraph(value = "author-books-graph", 
            type = EntityGraph.EntityGraphType.FETCH)    
    public List<Author> findByAgeGreaterThanAndGenre(int age, String genre);
    
    @EntityGraph(value = "author-books-graph", 
            type = EntityGraph.EntityGraphType.LOAD)   
    public List<Author> findByGenreAndAgeGreaterThan(String genre, int age);
}

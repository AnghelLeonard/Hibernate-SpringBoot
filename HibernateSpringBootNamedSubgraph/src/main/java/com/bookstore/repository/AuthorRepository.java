package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
   
    @EntityGraph(value = "author-books-publisher-graph", 
            type = EntityGraph.EntityGraphType.FETCH)
    @Override
    public List<Author> findAll();
    
    @EntityGraph(value = "author-books-publisher-graph", 
            type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books b "
            + "WHERE b.isbn LIKE '001-%'")
    public List<Author> fetchAll();
}
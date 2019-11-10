package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {
   
    @EntityGraph(value = "author-books-graph", 
            type = EntityGraph.EntityGraphType.FETCH)
    @Override
    public List<Author> findAll();
    
    @EntityGraph(value = "author-books-graph",
            type = EntityGraph.EntityGraphType.FETCH)
    public List<Author> findByAgeLessThanOrderByNameDesc(int age);
    
    @Override
    @EntityGraph(value = "author-books-graph",
            type = EntityGraph.EntityGraphType.FETCH)
    public List<Author> findAll(Specification spec);  
    
    @EntityGraph(value = "author-books-graph",
            type = EntityGraph.EntityGraphType.FETCH)
    @Query(value="SELECT a FROM Author a WHERE a.age > 20 AND a.age < 40")
    public List<Author> fetchAllAgeBetween20And40();      
}

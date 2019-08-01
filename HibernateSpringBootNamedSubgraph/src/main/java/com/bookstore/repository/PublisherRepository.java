package com.bookstore.repository;

import com.bookstore.entity.Publisher;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    
    @EntityGraph(attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Override
    public List<Publisher> findAll();
    
    @EntityGraph(attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM Publisher p LEFT JOIN FETCH p.books b "
            + "WHERE b.isbn LIKE '001-%'")
    public List<Publisher> fetchAll001();
}

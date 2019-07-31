package com.bookstore.repository;

import com.bookstore.entity.Publisher;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Transactional(readOnly = true)
    @EntityGraph(attributePaths = {"books", "books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Override
    public List<Publisher> findAll();
}

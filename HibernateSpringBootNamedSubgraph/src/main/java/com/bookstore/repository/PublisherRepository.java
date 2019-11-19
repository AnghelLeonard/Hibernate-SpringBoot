package com.bookstore.repository;

import com.bookstore.entity.Publisher;
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
public interface PublisherRepository extends JpaRepository<Publisher, Long>,
        JpaSpecificationExecutor<Publisher> {

    @EntityGraph(attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Override
    public List<Publisher> findAll();

    @EntityGraph(attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    public List<Publisher> findByIdLessThanOrderByCompanyDesc(long id);

    @EntityGraph(attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Override
    public List<Publisher> findAll(Specification<Publisher> spec);

    @EntityGraph(attributePaths = {"books.author"},
            type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM Publisher p WHERE p.id > 1 AND p.id < 3")    
    public List<Publisher> fetchAllIdBetween1And3();
}

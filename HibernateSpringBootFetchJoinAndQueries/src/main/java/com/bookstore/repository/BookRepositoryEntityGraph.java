package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryEntityGraph extends JpaRepository<Book, Long>,
        JpaSpecificationExecutor<Book> {

    @EntityGraph(attributePaths = {"author.publisher"})
    @Override
    public List<Book> findAll();

    @EntityGraph(attributePaths = {"author.publisher"})
    @Override
    public List<Book> findAll(Specification<Book> spec);
}

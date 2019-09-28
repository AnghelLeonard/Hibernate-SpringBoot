package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>,
        JpaSpecificationExecutor<Author> {
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.id FROM Author a WHERE a.genre = ?1")
    Page<Long> fetchPageOfIdsByGenre(String genre, Pageable pageable);
}

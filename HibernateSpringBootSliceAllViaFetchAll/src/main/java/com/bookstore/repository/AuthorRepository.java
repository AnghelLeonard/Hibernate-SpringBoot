package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT a FROM Author a")
    Slice<Author> fetchAll(Pageable pageable);
    
    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name as name, a.age as age FROM Author a")
    Slice<AuthorDto> fetchAllDto(Pageable pageable);
}

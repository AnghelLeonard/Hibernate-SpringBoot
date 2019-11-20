package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    List<Author> findByAgeGreaterThanEqual(int age);

    @Transactional(readOnly = true)
    @Query("SELECT a.name AS name, a.avatar AS avatar FROM Author a WHERE a.age >= ?1")
    List<AuthorDto> findDtoByAgeGreaterThanEqual(int age);
}

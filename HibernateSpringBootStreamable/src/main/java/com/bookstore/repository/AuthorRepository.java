package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.dto.AuthorName;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Streamable<Author> findByGenre(String genre);
    
    Streamable<AuthorName> queryByGenre(String genre);

    Streamable<Author> findByAgeGreaterThan(int age);

    Streamable<Author> findByGenreAndAgeGreaterThan(String genre, int age);

    Streamable<Author> findByGenreOrAgeGreaterThan(String genre, int age);

    Streamable<AuthorName> findBy();
}

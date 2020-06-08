package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    <T> List<T> findByGenre(String genre, Class<T> type);

    <T> T findByName(String name, Class<T> type);

    <T> T findByNameAndAge(String name, int age, Class<T> type);
}

package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM author AS a WHERE a.id > ?1 ORDER BY a.id ASC LIMIT ?2",
            nativeQuery = true)
    List<Author> fetchAllAuthors(long id, int limit);
}

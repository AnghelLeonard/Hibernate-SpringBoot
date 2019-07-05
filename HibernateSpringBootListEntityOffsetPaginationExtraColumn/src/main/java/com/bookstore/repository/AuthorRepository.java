package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query(value = "SELECT id, name, genre, age, t.total FROM author, "
            + "(SELECT count(*) AS total FROM author) AS t "
            + "ORDER BY age LIMIT ?1, ?2",
            nativeQuery = true)
    List<Author> fetchAll(int page, int size);
}

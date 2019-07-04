package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query(value = "SELECT t.total, name, age FROM author, "
            + "(SELECT count(*) AS total FROM author) AS t "
            + "ORDER BY age LIMIT ?1, ?2",
            nativeQuery = true)
    List<AuthorDto> fetchAll(int page, int size);
}

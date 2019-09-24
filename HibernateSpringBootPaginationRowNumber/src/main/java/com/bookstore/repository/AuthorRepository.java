package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    @Query(value = "SELECT * FROM (SELECT id, age, name, genre, COUNT(*) OVER() AS total, "
            + "ROW_NUMBER() OVER (ORDER BY age) AS row_num FROM author) AS a "
            + "WHERE row_num BETWEEN ?1 AND ?2",
            nativeQuery = true)
    List<Author> fetchPage(int start, int end);        
}

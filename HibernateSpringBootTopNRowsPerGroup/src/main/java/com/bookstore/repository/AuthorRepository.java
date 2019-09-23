package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    @Query(value = "WITH sales AS (SELECT *, ROW_NUMBER() "
            + "OVER (PARTITION BY name ORDER BY sold DESC) AS row_num"
            + " FROM author) SELECT * FROM sales WHERE row_num <= 2",
            nativeQuery = true)
    List<Author> fetchTop2BySales();
}

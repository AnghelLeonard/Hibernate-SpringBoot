package com.bookstore.repository;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    @Query(value = "SELECT * FROM (SELECT name, age, COUNT(*) OVER() AS total, "
            + "ROW_NUMBER() OVER (ORDER BY age) AS row_num FROM author) AS a "
            + "WHERE row_num BETWEEN ?1 AND ?2",
            nativeQuery = true)
    List<AuthorDto> fetchPage(int start, int end);
}

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

    @Query(value = "SELECT RANK() OVER(PARTITION BY genre ORDER BY name DESC) "
            + "rankNum, name, genre FROM author",
            nativeQuery = true)
    List<AuthorDto> fetchWithRank1();

    @Query(value = "SELECT RANK() OVER(ORDER BY genre) "
            + "rankNum, name, genre FROM author ORDER BY name",
            nativeQuery = true)
    List<AuthorDto> fetchWithRank2();
}

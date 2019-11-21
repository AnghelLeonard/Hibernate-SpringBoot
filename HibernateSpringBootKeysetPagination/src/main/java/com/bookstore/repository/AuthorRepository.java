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

    @Query(value = "SELECT * FROM author AS a WHERE a.id < ?1 ORDER BY a.id DESC LIMIT ?2",
            nativeQuery = true)
    List<Author> fetchAll(long id, int limit);

    @Query(value = "SELECT name, age FROM author AS a WHERE a.id < ?1 ORDER BY a.id DESC LIMIT ?2",
            nativeQuery = true)
    List<AuthorDto> fetchAllDto(long id, int limit);
}

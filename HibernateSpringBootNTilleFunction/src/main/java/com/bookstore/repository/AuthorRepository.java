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

    @Query(value = "SELECT NTILE(2) OVER(ORDER BY age) "
            + "tile, name, genre, age FROM author",
            nativeQuery = true)
    List<AuthorDto> fetchWithNTile1();

    @Query(value = "SELECT NTILE(3) OVER(ORDER BY age) "
            + "tile, name, genre, age FROM author",
            nativeQuery = true)
    List<AuthorDto> fetchWithNTile2();
    
    @Query(value = "SELECT NTILE(3) OVER(PARTITION BY age ORDER BY age DESC) "
            + "tile, name, genre, age FROM author",
            nativeQuery = true)
    List<AuthorDto> fetchWithNTile3();
}

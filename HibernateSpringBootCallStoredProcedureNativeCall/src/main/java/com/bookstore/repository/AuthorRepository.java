package com.bookstore.repository;

import com.bookstore.dto.AuthorNicknameAndAge;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "{CALL FETCH_AUTHOR_BY_GENRE (:p_genre)}", nativeQuery = true)
    List<Author> fetchByGenre(@Param("p_genre") String genre);

    @Query(value = "{CALL FETCH_NICKNAME_AND_AGE_BY_GENRE (:p_genre)}", nativeQuery = true)
    List<Object[]> fetchNicknameAndAgeByGenreProj(@Param("p_genre") String genre);

    @Query(value = "{CALL FETCH_NICKNAME_AND_AGE_BY_GENRE (:p_genre)}", nativeQuery = true)
    List<AuthorNicknameAndAge> fetchNicknameAndAgeByGenreDto(@Param("p_genre") String genre);
}

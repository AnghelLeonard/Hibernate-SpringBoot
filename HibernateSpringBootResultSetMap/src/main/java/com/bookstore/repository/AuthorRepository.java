package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    default Map<Long, Author> fetchIdAuthor() {

        return findAll().stream()
                .collect(Collectors.toMap(Author::getId, Function.identity()));
    }

    @Transactional(readOnly = true)
    @Query("SELECT a.genre AS genre, count(a) AS genreCount FROM Author a GROUP BY a.genre")
    List<Object[]> groupByGenreObj();

    default Map<String, Long> groupByGenre() {

        return groupByGenreObj()
                .stream()
                .collect(
                        Collectors.toMap(
                                t -> ((String) t[0]),
                                t -> ((long) t[1])
                        )
                );
    }
}

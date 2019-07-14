package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.stream.Stream;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a")
    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    Stream<Author> streamAll();
}

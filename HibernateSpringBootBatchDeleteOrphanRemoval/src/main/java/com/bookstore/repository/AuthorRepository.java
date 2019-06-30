package com.bookstore.repository;

import com.bookstore.entity.Author;
import java.util.List;
import javax.persistence.QueryHint;
import static org.hibernate.jpa.QueryHints.HINT_PASS_DISTINCT_THROUGH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))
    @Query(value = "SELECT DISTINCT a FROM Author a JOIN FETCH a.books b WHERE a.age < ?1")
    List<Author> fetchAuthorsAndBooks(int age);
}

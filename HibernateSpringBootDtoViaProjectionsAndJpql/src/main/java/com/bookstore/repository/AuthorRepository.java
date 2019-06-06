package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.projection.AuthorNameAge;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional(readOnly = true)
    @Query(value = "SELECT a.name AS name, a.age AS age FROM Author a WHERE a.genre=?1")
    List<AuthorNameAge> fetchByGenre(String genre);
}

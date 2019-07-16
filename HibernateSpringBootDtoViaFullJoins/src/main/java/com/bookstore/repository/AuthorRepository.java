package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.projection.AuthorNameBookTitle;

@Repository
@Transactional(readOnly = true)
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Fetch authors and books including authors that have no registered books and books with no registered authors (JPQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Author a FULL JOIN a.books b")
    List<AuthorNameBookTitle> findAuthorsAndBooksJpql();

    // Fetch authors and books including authors that have no registered books and books with no registered authors (SQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM author a FULL JOIN book b ON a.id = b.author_id",
            nativeQuery = true)
    List<AuthorNameBookTitle> findAuthorsAndBooksSql();    
}
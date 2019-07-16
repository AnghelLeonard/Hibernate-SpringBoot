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

    // Fetch authors and books excluding authors that have registered books (JPQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Author a RIGHT JOIN a.books b WHERE a.id IS NULL")
    List<AuthorNameBookTitle> findAuthorsAndBooksJpql();

    // Fetch authors and books excluding authors that have registered books (SQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM author a RIGHT JOIN book b ON a.id = b.author_id WHERE a.id IS NULL",
            nativeQuery = true)
    List<AuthorNameBookTitle> findAuthorsAndBooksSql();    
}
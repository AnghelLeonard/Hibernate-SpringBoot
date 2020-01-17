package com.bookstore.repository;

import java.util.List;
import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.projection.AuthorNameBookTitle;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends JpaRepository<Book, Long> {

    // Fetch books and authors excluding authors that have registered books and books with registered authors (JPQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Book b FULL JOIN b.author a WHERE b.id IS NULL OR a.id IS NULL")
    List<AuthorNameBookTitle> findBooksAndAuthorsJpql();

    // Fetch books and authors excluding authors that have registered books and books with registered authors (SQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM book b FULL JOIN author a ON a.id = b.author_id WHERE b.id IS NULL OR a.id IS NULL",
            nativeQuery = true)
    List<AuthorNameBookTitle> findBooksAndAuthorsSql();        
}

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

    // Fetch authors and books (JPQL)    
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Author a INNER JOIN a.books b")
    List<AuthorNameBookTitle> findAuthorsAndBooksJpql();

    // Fetch authors and books (SQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM author a INNER JOIN book b ON a.id = b.author_id",
            nativeQuery = true)
    List<AuthorNameBookTitle> findAuthorsAndBooksSql();

    // Fetch authors and books filtering by author's genre and book's price (JPQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM Author a INNER JOIN a.books b WHERE a.genre = ?1 AND b.price < ?2")
    List<AuthorNameBookTitle> findAuthorsAndBooksByGenreAndPriceJpql(String genre, int price);

    // Fetch authors and books filtering by author's genre and book's price (SQL)
    @Query(value = "SELECT b.title AS title, a.name AS name "
            + "FROM author a INNER JOIN book b ON a.id = b.author_id WHERE a.genre = ?1 AND b.price < ?2",
            nativeQuery = true)
    List<AuthorNameBookTitle> findAuthorsAndBooksByGenreAndPriceSql(String genre, int price);
}
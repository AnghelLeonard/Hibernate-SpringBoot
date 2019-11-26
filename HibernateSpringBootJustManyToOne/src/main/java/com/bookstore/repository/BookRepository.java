package com.bookstore.repository;

import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.author.id = :id")
    List<Book> fetchBooksOfAuthorById(Long id);

    @Query("SELECT b FROM Book b WHERE b.author.id = :id")
    Page<Book> fetchPageBooksOfAuthorById(Long id, Pageable pageable);
}

package com.bookstore.repository;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByAuthor(Author author);
    
    List<BookDto> queryByAuthor(Author author);
}

package com.bookstore.service;

import com.bookstore.dao.Dao;
import com.bookstore.dto.AuthorDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final Dao dao;

    public BookstoreService(Dao dao) {
        this.dao = dao;
    }

   public List<AuthorDto> fetchAuthorWithBook() {
       
       List<AuthorDto> authors = dao.fetchAuthorWithBook();       
       authors.forEach(a -> System.out.println("\n" + a + " | books: " + a.getBooks()));
       
       return authors;
   }
}

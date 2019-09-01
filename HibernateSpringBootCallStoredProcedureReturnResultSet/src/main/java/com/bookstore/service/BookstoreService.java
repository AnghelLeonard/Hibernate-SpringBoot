package com.bookstore.service;

import com.bookstore.dao.Dao;
import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final Dao dao;

    public BookstoreService(Dao dao) {
        this.dao = dao;
    }

    public void fetchAnthologyAuthorsViaNamedStoredProcedure() {
        List<Author> authors = dao.fetchByGenre1("Anthology");
        authors.forEach(a -> System.out.println(a));
    }

    public void fetchAnthologyAuthorsViaStoredProcedure() {
        List<Author> authors = dao.fetchByGenre2("Anthology");
        authors.forEach(a -> System.out.println(a));
    }

    public void fetchAnthologyDtoAuthorsViaStoredProcedure() {
        List<AuthorDto> authors = dao.fetchByGenre3("Anthology");
        authors.forEach(a -> System.out.println(a));
    }
}

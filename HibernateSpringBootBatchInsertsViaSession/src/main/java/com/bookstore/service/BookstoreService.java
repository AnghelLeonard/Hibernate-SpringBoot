package com.bookstore.service;

import com.bookstore.dao.Dao;
import java.util.ArrayList;
import java.util.List;
import com.bookstore.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final Dao dao;

    public BookstoreService(Dao dao) {
        this.dao = dao;
    }

    public void batchAuthors() {

        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Author author = new Author();
            author.setId((long) i + 1);
            author.setName("Name_" + i);
            author.setGenre("Genre_" + i);
            author.setAge(18 + i);

            authors.add(author);
        }

        dao.saveInBatch(authors);
    }
}

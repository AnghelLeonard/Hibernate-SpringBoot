package com.bookstore.service;

import com.bookstore.dao.Dao;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bookstore.dto.AuthorDto;

@Service
public class BookstoreService {

    private final Dao dao;

    public BookstoreService(Dao dao) {
        this.dao = dao;
    }

    public List<AuthorDto> fetchNameAndAge() {

        return dao.fetchNameAndAge();
    }
}

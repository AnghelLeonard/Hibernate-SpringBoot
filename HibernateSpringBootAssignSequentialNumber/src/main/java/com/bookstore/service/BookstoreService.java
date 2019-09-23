package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void fetchAuthorsWithSeqNumber() {
        List<AuthorDto> authors = authorRepository.fetchWithSeqNumber();
        
        authors.forEach(a -> System.out.println(a.getRowNum() 
                + ", " + a.getName() + ", " + a.getName()));
    }
}

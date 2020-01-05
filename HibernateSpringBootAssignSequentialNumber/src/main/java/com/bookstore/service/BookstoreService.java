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

    public void fetchAuthorsWithSeqNumber1() {
        List<AuthorDto> authors = authorRepository.fetchWithSeqNumber1();
        
        authors.forEach(a -> System.out.println(a.getRowNum() 
                + ", " + a.getName() + ", " + a.getAge()));
    }
    
    public void fetchAuthorsWithSeqNumber2() {
        List<AuthorDto> authors = authorRepository.fetchWithSeqNumber2();
        
        authors.forEach(a -> System.out.println(a.getRowNum() 
                + ", " + a.getName() + ", " + a.getAge()));
    }
    
    public void fetchAuthorsWithSeqNumber3() {
        List<AuthorDto> authors = authorRepository.fetchWithSeqNumber3();
        
        authors.forEach(a -> System.out.println(a.getRowNum() 
                + ", " + a.getName() + ", " + a.getAge()));
    }
    
        public void fetchAuthorsWithSeqNumber4() {
        List<AuthorDto> authors = authorRepository.fetchWithSeqNumber4();
        
        authors.forEach(a -> System.out.println(a.getRowNum() 
                + ", " + a.getName() + ", " + a.getAge()));
    }
}

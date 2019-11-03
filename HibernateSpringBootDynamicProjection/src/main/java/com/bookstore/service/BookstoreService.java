package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.entity.Author;
import java.util.List;

@Service
public class BookstoreService {
    
    private final AuthorRepository authorRepository;
    
    public BookstoreService(AuthorRepository authorRepository) {
        
        this.authorRepository = authorRepository;
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorByNameAsEntityJpql() {
        Author author = authorRepository.findByName("Joana Nimar", Author.class);
        
        System.out.println(author);
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorByNameAsDtoJpql() {
        AuthorDto author = authorRepository.findByName("Joana Nimar", AuthorDto.class);
        
        System.out.println(author.getEmail() + ", " + author.getName());
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorByNameAndAgeAsEntityJpql() {
        Author author = authorRepository.findByNameAndAge("Joana Nimar", 34, Author.class);
        
        System.out.println(author);
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorByNameAndAgeAsDtoJpql() {
        AuthorDto author = authorRepository.findByNameAndAge("Joana Nimar", 34, AuthorDto.class);
        
        System.out.println(author.getEmail() + ", " + author.getName());
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorsAsEntitiesJpql() {
        List<Author> authors = authorRepository.findByGenre("Anthology", Author.class);
        
        System.out.println(authors);
    }
    
    @Transactional(readOnly = true)
    public void fetchAuthorsAsDtoJpql() {
        List<AuthorDto> authors = authorRepository.findByGenre("Anthology", AuthorDto.class);
        
        authors.forEach(a -> System.out.println(a.getName() + ", " + a.getEmail()));
    }
    
}

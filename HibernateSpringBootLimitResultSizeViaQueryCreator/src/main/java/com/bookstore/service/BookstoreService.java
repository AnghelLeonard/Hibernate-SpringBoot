package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> fetchTop5ByAge(int age) {
        return authorRepository.findTop5ByAge(age);
    }

    public List<Author> fetchFirst5ByAge(int age, Sort sort) {
        return authorRepository.findFirst5ByAge(age, sort);
    }

    public List<Author> fetchTop3ByAgeGreaterThanEqual(int age) {
        return authorRepository.findTop3ByAgeGreaterThanEqual(age);
    }

    public List<Author> fetchFirst3ByAgeGreaterThan(int age, Sort sort) {
        return authorRepository.findFirst3ByAgeGreaterThan(age, sort);
    }

    public List<Author> fetchFirst5ByOrderByAgeDesc() {
        return authorRepository.findFirst5ByOrderByAgeDesc();
    }

    public List<Author> fetchTop3ByOrderByAgeDesc() {
        return authorRepository.findTop3ByOrderByAgeDesc();
    }
    
    public List<AuthorDto> fetchTop3ByOrderByAgeAsc() {
        return authorRepository.findTop3ByOrderByAgeAsc();
    }
}

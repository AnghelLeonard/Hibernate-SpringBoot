package com.bookstore.service;

import com.bookstore.view.GenreAndTitleView;
import org.springframework.stereotype.Service;
import com.bookstore.repository.AuthorBookViewRepository;
import java.util.List;

@Service
public class BookstoreService {
   
    private final AuthorBookViewRepository authorBookViewRepository;    

    public BookstoreService(AuthorBookViewRepository authorBookViewRepository) {
        this.authorBookViewRepository = authorBookViewRepository;        
    }
   
    public void displayView() {
        List<GenreAndTitleView> view = authorBookViewRepository.findAll();
        System.out.println("View: " + view);
    }
}

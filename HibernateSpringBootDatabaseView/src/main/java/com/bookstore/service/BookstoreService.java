package com.bookstore.service;

import com.bookstore.view.GenreAndTitleView;
import org.springframework.stereotype.Service;
import java.util.List;
import com.bookstore.repository.GenreAndTitleViewRepository;

@Service
public class BookstoreService {
   
    private final GenreAndTitleViewRepository genreAndTitleViewRepository;    

    public BookstoreService(GenreAndTitleViewRepository genreAndTitleViewRepository) {
        this.genreAndTitleViewRepository = genreAndTitleViewRepository;        
    }
   
    public void displayView() {
        List<GenreAndTitleView> view = genreAndTitleViewRepository.findAll();
        System.out.println("View: " + view);
    }
    
    public void displayViewByGenre() {
        List<GenreAndTitleView> view = genreAndTitleViewRepository.findByGenre("History");
        System.out.println("View: " + view);
    }
}

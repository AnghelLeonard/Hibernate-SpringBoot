package com.bookstore.service;

import com.bookstore.view.NameAndGenreView;
import org.springframework.stereotype.Service;
import java.util.List;
import com.bookstore.repository.NameAndGenreViewRepository;

@Service
public class BookstoreService {
   
    private final NameAndGenreViewRepository nameAndGenreViewRepository;    

    public BookstoreService(NameAndGenreViewRepository nameAndGenreViewRepository) {
        this.nameAndGenreViewRepository = nameAndGenreViewRepository;        
    }
   
    public void displayView() {
        List<NameAndGenreView> view = nameAndGenreViewRepository.findAll();
        System.out.println("View: " + view);
    }
    
    // Works
    public void insertAnthologyInView() {
        NameAndGenreView author = new NameAndGenreView();
        author.setName("Mark Powell");
        author.setGenre("Anthology");
        author.setId(100L);
        
        nameAndGenreViewRepository.save(author);
    }
    
    // WITH CHECK OPTION doesn't allow this insert
    // expect to see: java.sql.SQLException: CHECK OPTION failed 'bookstoredb.name_and_genre_view'
    public void insertHistoryInView() {
        NameAndGenreView author = new NameAndGenreView();
        author.setName("Mark Powell");
        author.setGenre("History"); // this field doesn't pass the check
        author.setId(101L);
        
        nameAndGenreViewRepository.save(author);
    }
}

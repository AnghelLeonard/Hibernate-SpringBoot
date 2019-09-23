package com.bookstore.service;

import com.bookstore.view.AuthorAnthologyView;
import org.springframework.stereotype.Service;
import com.bookstore.repository.AuthorAnthologyViewRepository;

@Service
public class BookstoreService {
   
    private final AuthorAnthologyViewRepository authorAnthologyViewRepository;    

    public BookstoreService(AuthorAnthologyViewRepository authorAnthologyViewRepository) {
        this.authorAnthologyViewRepository = authorAnthologyViewRepository;        
    }  
    
    // Works
    public void insertAnthologyAuthorInView() {
        AuthorAnthologyView author = new AuthorAnthologyView();
        author.setName("Mark Powell");
        author.setGenre("Anthology");
        author.setAge(45);
        
        authorAnthologyViewRepository.save(author);
    }
    
    // Doesn't work
    // WITH CHECK OPTION doesn't allow this insert
    // expect to see: java.sql.SQLException: CHECK OPTION failed 'bookstoredb.author_anthology_view'
    public void insertHistoryAuthorInView() {
        AuthorAnthologyView author = new AuthorAnthologyView();
        author.setName("Mark Powell");
        author.setGenre("History"); // this field doesn't pass the check
        author.setAge(45);
        
        authorAnthologyViewRepository.save(author);
    }
}

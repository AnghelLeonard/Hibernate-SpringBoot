package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly=true)    
    public void fetchAuthorsIn() {
        
        List twoIds = List.of(1L, 2L);
        List threeIds = List.of(1L, 2L, 3L);
        List fourIds = List.of(1L, 2L, 3L, 4L);
        List fiveIds = List.of(1L, 2L, 3L, 4L, 5L);
        List sixIds = List.of(1L, 2L, 3L, 4L, 5L, 6L);
        List sevenIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L);
        List eightIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L);
        List nineIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);
        List tenIds = List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        
        authorRepository.fetchIn(twoIds);
        authorRepository.fetchIn(threeIds);
        authorRepository.fetchIn(fourIds);
        authorRepository.fetchIn(fiveIds);                        
        authorRepository.fetchIn(sixIds);                        
        authorRepository.fetchIn(sevenIds);                        
        authorRepository.fetchIn(eightIds);                        
        authorRepository.fetchIn(nineIds);                        
        authorRepository.fetchIn(tenIds);                        
    }
}

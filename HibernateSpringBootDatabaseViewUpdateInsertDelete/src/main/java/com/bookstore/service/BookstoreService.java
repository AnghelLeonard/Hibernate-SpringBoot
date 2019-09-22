package com.bookstore.service;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import com.bookstore.view.AuthorAnthologyView;
import com.bookstore.repository.AuthorAnthologyViewRepository;

@Service
public class BookstoreService {

    private final AuthorAnthologyViewRepository authorAnthologyViewRepository;

    public BookstoreService(AuthorAnthologyViewRepository authorAnthologyViewRepository) {
        this.authorAnthologyViewRepository = authorAnthologyViewRepository;
    }

    @Transactional
    public void updateAuthorAgeViaView() {
        AuthorAnthologyView author
                = authorAnthologyViewRepository.findByName("Quartis Young");

        author.setAge(author.getAge() + 1);
    }

    public void insertAuthorViaView() {
        AuthorAnthologyView newAuthor = new AuthorAnthologyView();
        newAuthor.setName("Toij Kalu");
        newAuthor.setGenre("Anthology");
        newAuthor.setAge(42);

        authorAnthologyViewRepository.save(newAuthor);
    }
    
    @Transactional
    public void deleteAuthorViaView() {
        AuthorAnthologyView author
                = authorAnthologyViewRepository.findByName("Mark Janel");
        
        authorAnthologyViewRepository.delete(author);
    }    
}

package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void authorBatchInserts() {
        Author jn = new Author();
        jn.setName("Joana Nimar");
        jn.setGenre("History");
        jn.setAge(34);
        
        Author mj = new Author();
        mj.setName("Mark Janel");
        mj.setGenre("Anthology");
        mj.setAge(23);
        
        Author og = new Author();
        og.setName("Olivia Goy");
        og.setGenre("Horror");
        og.setAge(43);
        
        Author qy = new Author();
        qy.setName("Quartis Young");
        qy.setGenre("Anthology");
        qy.setAge(51);
        
        Author at = new Author();
        at.setName("Alicia Tom");
        at.setGenre("Anthology");
        at.setAge(38);
        
        Author kl = new Author();
        kl.setName("Katy Loin");
        kl.setGenre("Anthology");
        kl.setAge(56);
        
        List<Author> authors = Arrays.asList(jn, mj, og, qy, at, kl);
        
        authorRepository.saveAll(authors);
    }
}

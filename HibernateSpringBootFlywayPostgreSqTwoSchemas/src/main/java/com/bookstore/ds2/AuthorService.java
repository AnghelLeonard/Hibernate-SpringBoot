package com.bookstore.ds2;

import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author persistAuthor() {
        Author author = new Author();

        author.setName("Joana Nimar");
        author.setGenre("History");
        author.setAge(34);
        author.setBooks("A History of Ancient Prague, A People's History");

        return authorRepository.save(author);
    }
}

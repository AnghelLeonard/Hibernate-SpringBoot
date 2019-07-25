package com.bookstore.service;

import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {
   
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
   
    public void addAuthorWithBooks(){
        
        Author author = new Author();
        author.setName("Alicia Tom");
        author.setAge(38);
        author.setGenre("Anthology");
        
        Book book = new Book();
        book.setIsbn("001-AT");
        book.setTitle("The book of swords");
                
        author.addBook(book); // use addBook() helper
        
        authorRepository.save(author);
    }
    
    @Transactional
    public void removeBookOfAuthor() {
        
        Author author = authorRepository.findByName("Alicia Tom");
        Book book  = bookRepository.findByTitle("The book of swords");
        
        author.removeBook(book); // use removeBook() helper        
    }
        
    @Transactional
    public void removeAllBooksOfAuthor() {
        Author author = authorRepository.findByName("Joana Nimar");
        author.removeBooks(); // use removeBooks() helper    
    }

}

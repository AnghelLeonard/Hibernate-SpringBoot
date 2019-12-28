package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.repository.BookRepositoryEntityGraph;
import com.bookstore.repository.BookRepositoryNPlus1;
import static com.bookstore.specs.BookSpecs.isPriceGt35;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final BookRepositoryNPlus1 bookRepositoryNplus1;
    private final BookRepositoryEntityGraph bookRepositoryEntityGraph;

    public BookstoreService(BookRepositoryNPlus1 bookRepositoryNplus1,
            BookRepositoryEntityGraph bookRepositoryEntityGraph) {

        this.bookRepositoryNplus1 = bookRepositoryNplus1;
        this.bookRepositoryEntityGraph = bookRepositoryEntityGraph;
    }
    
    public void displayBooksCausingNPlus1()  {
        
        List<Book> books = bookRepositoryNplus1.findAll(); // N+1         
        display(books);
    }   
    
    public void displayBooksOfAuthorsAgeGt45CausingNPlus1()  {
        
        List<Book> books = bookRepositoryNplus1.findAll(isPriceGt35()); // N+1         
        display(books);
    }
    
    public void displayBooksViaEntityGraph() {
        
        List<Book> books = bookRepositoryEntityGraph.findAll(); // LEFT JOIN
        display(books);
    }
    
    public void displayBooksOfAuthorsAgeGt45ViaEntityGraph()  {
        
        List<Book> books = bookRepositoryEntityGraph.findAll(isPriceGt35()); // LEFT JOIN
        display(books);
    }
    
    private void display(List<Book> books) {
        
        for(Book book: books) {
            System.out.println(book);
            System.out.println(book.getAuthor());
            System.out.println(book.getAuthor().getPublisher() + "\n");
        }
    }
}

package modern.challenge.service;

import java.util.Set;
import modern.challenge.repository.BookRepository;
import modern.challenge.repository.AuthorRepository;
import java.util.logging.Logger;
import modern.challenge.entity.Author;
import modern.challenge.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private static final Logger logger
            = Logger.getLogger(BookstoreService.class.getName());

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookstoreService(AuthorRepository authorRepository,
            BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void registerAuthor() {

        Author author1 = new Author();
        author1.setName("Name_1");
        author1.setSurname("Surname_1");
        author1.setAge(43);

        Author author2 = new Author();
        author2.setName("Name_2");
        author2.setSurname("Surname_2");
        author2.setAge(41);

        Book book1 = new Book();
        book1.setIsbn("Isbn_1");
        book1.setTitle("Title_1");

        Book book2 = new Book();
        book2.setIsbn("Isbn_2");
        book2.setTitle("Title_2");

        Book book3 = new Book();
        book3.setIsbn("Isbn_3");
        book3.setTitle("Title_3");

        author1.addBook(book1);
        author1.addBook(book2);
        author2.addBook(book1);
        author2.addBook(book2);
        author2.addBook(book3);

        authorRepository.save(author1);
        authorRepository.save(author2);
    }

    @Transactional
    public void updateAuthor() {
        Author author = authorRepository.findByName("Name_1");
        
        author.setAge(45);
    }

    @Transactional
    public void updateBooks() {
        Author author = authorRepository.findByName("Name_1");
        Set<Book> books = author.getBooks();
        
        for(Book book: books) {
            book.setIsbn("not available");
        }
    }
}

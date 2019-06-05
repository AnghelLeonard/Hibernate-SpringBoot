package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void createAuthors() throws IOException {

        Author mt = new Author();
        mt.setId(1L);
        mt.setName("Martin Ticher");
        mt.setAge(43);
        mt.setGenre("Horror");
        mt.setAvatar(Files.readAllBytes(new File("avatars/mt.png").toPath()));

        Author cd = new Author();
        cd.setId(2L);
        cd.setName("Carla Donnoti");
        cd.setAge(31);
        cd.setGenre("Science Fiction");
        cd.setAvatar(Files.readAllBytes(new File("avatars/cd.png").toPath()));

        Author re = new Author();
        re.setId(3L);
        re.setName("Rennata Elibol");
        re.setAge(46);
        re.setGenre("Fantasy");
        re.setAvatar(Files.readAllBytes(new File("avatars/re.png").toPath()));

        authorRepository.save(mt);
        authorRepository.save(cd);
        authorRepository.save(re);
    }

    public List<Author> fetchAuthorsByAgeGreaterThanEqual(int age) {
        List<Author> authors = authorRepository.findByAgeGreaterThanEqual(age);

        return authors;
    }  

    @Transactional(readOnly = true)
    public byte[] fetchAuthorAvatarViaId(long id) {

        Author author = authorRepository.getOne(id);
        return author.getAvatar();
    }

    @Transactional(readOnly = true)
    public List<Author> fetchAuthorsDetailsByAgeGreaterThanEqual(int age) {

        List<Author> authors = authorRepository.findByAgeGreaterThanEqual(age);

        // don't do this since this is a N+1 case
        authors.forEach(a -> {
            a.getAvatar();     
        });

        return authors;
    }
}

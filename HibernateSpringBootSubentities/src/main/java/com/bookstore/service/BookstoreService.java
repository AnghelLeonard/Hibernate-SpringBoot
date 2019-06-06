package com.bookstore.service;

import com.bookstore.entity.AuthorDeep;
import com.bookstore.entity.AuthorShallow;
import com.bookstore.repository.AuthorDeepRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookstore.repository.AuthorShallowRepository;
import java.util.List;

@Service
public class BookstoreService {

    private final AuthorShallowRepository authorShallowRepository;
    private final AuthorDeepRepository authorDeepRepository;

    public BookstoreService(AuthorShallowRepository authorShallowRepository,
            AuthorDeepRepository authorDeepRepository) {
        this.authorShallowRepository = authorShallowRepository;
        this.authorDeepRepository = authorDeepRepository;
    }

    @Transactional
    public void createAuthors() throws IOException {

        AuthorDeep mt = new AuthorDeep();
        mt.setId(1L);
        mt.setName("Martin Ticher");
        mt.setAge(43);
        mt.setGenre("Horror");
        mt.setAvatar(Files.readAllBytes(new File("avatars/mt.png").toPath()));

        AuthorDeep cd = new AuthorDeep();
        cd.setId(2L);
        cd.setName("Carla Donnoti");
        cd.setAge(31);
        cd.setGenre("Science Fiction");
        cd.setAvatar(Files.readAllBytes(new File("avatars/cd.png").toPath()));

        AuthorDeep re = new AuthorDeep();
        re.setId(3L);
        re.setName("Rennata Elibol");
        re.setAge(46);
        re.setGenre("Fantasy");
        re.setAvatar(Files.readAllBytes(new File("avatars/re.png").toPath()));

        authorDeepRepository.save(mt);
        authorDeepRepository.save(cd);
        authorDeepRepository.save(re);
    }

    public List<AuthorShallow> fetchAuthorsShallow() {
        List<AuthorShallow> authors = authorShallowRepository.findAll();

        return authors;
    }

    public List<AuthorDeep> fetchAuthorsDeep() {
        List<AuthorDeep> authors = authorDeepRepository.findAll();

        return authors;
    }
}

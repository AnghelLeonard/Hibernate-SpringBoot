package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.partitioningBy;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void fetchSomeAuthorsAsDTOAndUpdate() {

        Streamable<AuthorDto> authorsDto = authorRepository.findByGenre("Anthology")
                .map(a -> {
                    a.setAge(a.getAge() + 1);
                    return a;
                })
                .filter(a -> a.getAge() > 40)
                .map(a -> new AuthorDto(a.getName(), a.getAge()));
        
        authorsDto.forEach(System.out::println);
    }

    public void fetchAuthorsByGenrePartitioningByAge() {

        Map<Boolean, List<Author>> authorsMap = authorRepository.findByGenre("Anthology")
                .get()
                .collect(partitioningBy(a -> a.getAge() > 40));

        System.out.println(authorsMap);
    }

    public void fetchAuthorsNamesAsString() {

        String authorsNames = authorRepository.findBy()
                .map(a -> a.getName().toUpperCase())
                .get()
                .collect(Collectors.joining("; "));

        System.out.println(authorsNames);
    }

    // Caution: Don't do this! Fetching all columns just to drop a part of them
    public void fetchAuthorsNames() {

        Streamable<String> authors = authorRepository.queryBy()
                .map(Author::getName);

        authors.forEach(System.out::println);
    }

    // Caution: Don't do this! Fetching all rows just to throw away a part of it
    public void fetchAuthorsOlderThan40() {

        Streamable<Author> authors = authorRepository.queryBy()
                .filter(a -> a.getAge() > 40);

        authors.forEach(System.out::println);
    }

    // Caution: Don't do this! It triggers two SELECT statements!
    @Transactional(readOnly = true)
    public void fetchAuthorsByGenreAndAge() {

        Streamable<Author> authors = authorRepository.findByGenre("Anthology")
                .and(authorRepository.findByAgeGreaterThan(40));

        authors.forEach(System.out::println);
    }
}

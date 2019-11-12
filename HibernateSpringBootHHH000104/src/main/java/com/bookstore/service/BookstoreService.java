package com.bookstore.service;

import com.bookstore.repository.AuthorRepository;
import com.bookstore.entity.Author;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;

    public BookstoreService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Page<Author> fetchPageOfAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Page<Long> pageOfIds = authorRepository.fetchPageOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooksJoinFetch(pageOfIds.getContent());
        Page<Author> pageOfAuthors = new PageImpl(listOfAuthors, pageable, pageOfIds.getTotalElements());

        return pageOfAuthors;
    }

    @Transactional
    public Slice<Author> fetchSliceOfAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Slice<Long> pageOfIds = authorRepository.fetchSliceOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooksJoinFetch(pageOfIds.getContent());
        Slice<Author> sliceOfAuthors = new SliceImpl(listOfAuthors, pageable, pageOfIds.hasNext());

        return sliceOfAuthors;
    }

    @Transactional
    public List<Author> fetchListOfAuthorsWithBooksByGenre(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        List<Long> listOfIds = authorRepository.fetchListOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooksJoinFetch(listOfIds);

        return listOfAuthors;
    }

    @Transactional
    public Page<Author> fetchPageOfAuthorsWithBooksByGenreEntityGraph(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        Page<Long> pageOfIds = authorRepository.fetchPageOfIdsByGenre("Anthology", pageable);
        List<Author> listOfAuthors = authorRepository.fetchWithBooksEntityGraph(pageOfIds.getContent());
        Page<Author> pageOfAuthors = new PageImpl(listOfAuthors, pageable, pageOfIds.getTotalElements());

        return pageOfAuthors;
    }
    
    @Transactional
    public Page<Author> fetchPageOfAuthorsWithBooksByGenreTuple(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name"));

        List<Tuple> tuples = authorRepository.fetchTupleOfIdsByGenre("Anthology", pageable);
        
        List<Long> listOfIds = new ArrayList<>(tuples.size());
        for(Tuple tuple: tuples) {
            listOfIds.add(((BigInteger) tuple.get("id")).longValue());
        }
        
        List<Author> listOfAuthors = authorRepository.fetchWithBooksJoinFetch(listOfIds);
        Page<Author> pageOfAuthors = new PageImpl(listOfAuthors, pageable, ((BigInteger) tuples.get(0).get("total")).longValue());

        return pageOfAuthors;
    }
}

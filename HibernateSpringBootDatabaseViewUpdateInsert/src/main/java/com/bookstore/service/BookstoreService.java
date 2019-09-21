package com.bookstore.service;

import com.bookstore.view.AuthorGenrePromoView;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.transaction.Transactional;
import com.bookstore.repository.AuthorGenrePromoViewRepository;
import com.bookstore.repository.AuthorNameAgeGenreViewRepository;
import com.bookstore.view.AuthorNameAgeGenreView;

@Service
public class BookstoreService {

    private final AuthorGenrePromoViewRepository authorGenrePromoViewRepository;
    private final AuthorNameAgeGenreViewRepository authorNameAgeGenreViewRepository;

    public BookstoreService(AuthorGenrePromoViewRepository authorGenrePromoViewRepository,
            AuthorNameAgeGenreViewRepository authorNameAgeGenreViewRepository) {
        this.authorGenrePromoViewRepository = authorGenrePromoViewRepository;
        this.authorNameAgeGenreViewRepository = authorNameAgeGenreViewRepository;
    }

    @Transactional
    public void updateView() {
        List<AuthorGenrePromoView> authorView
                = authorGenrePromoViewRepository.findAll();

        authorView.stream()
                .filter(a -> ("Anthology".equals(a.getGenre())))
                .forEachOrdered(a -> a.setPromotionFlag("High"));
    }

    public void insertView() {
        AuthorNameAgeGenreView newAuthor = new AuthorNameAgeGenreView();
        newAuthor.setName("Toij Kalu");
        newAuthor.setGenre("Anthology");
        newAuthor.setAge(42);

        authorNameAgeGenreViewRepository.save(newAuthor);
    }
}

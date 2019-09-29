package com.bookstore.service;

import com.bookstore.entity.Review;
import com.bookstore.repository.ArticleRepository;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.MagazineRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.bookstore.repository.ReviewRepository;

@Service
public class BookstoreService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ArticleRepository articleRepository;
    private final MagazineRepository magazineRepository;

    public BookstoreService(ReviewRepository reviewRepository, BookRepository bookRepository,
            ArticleRepository articleRepository, MagazineRepository magazineRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.articleRepository = articleRepository;
        this.magazineRepository = magazineRepository;
    }

    @Transactional
    public void persistReviewOk() {

        Review review = new Review();
        review.setContent("This is a book review ...");
        review.setBook(bookRepository.findById(1L).get());
        
        reviewRepository.save(review);
    }
    
    // Calling this method will cause a javax.validation.ConstraintViolationException
    // 'A review can be associated with either a book, a magazine or an article'
    @Transactional
    public void persistReviewWrong() {

        Review review = new Review();
        review.setContent("This is an article and magazine review ...");
        review.setArticle(articleRepository.findById(1L).get());
        review.setMagazine(magazineRepository.findById(1L).get());
        
        reviewRepository.save(review);
    }
}

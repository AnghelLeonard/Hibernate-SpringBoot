package com.bookstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id    
    private Long id;

    private String title;
    private String isbn;
    private String author;
         
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "book", orphanRemoval = true)
    private List<BookReview> reviews = new ArrayList<>();

    public void addReview(BookReview review) {
        this.reviews.add(review);
        review.setBook(this);
    }

    public void removeReview(BookReview review) {
        review.setBook(null);
        this.reviews.remove(review);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }       

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }       

    public List<BookReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<BookReview> reviews) {
        this.reviews = reviews;
    }
}

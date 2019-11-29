package com.bookstore.event;

import com.bookstore.entity.BookReview;

public class CheckReviewEvent {    
    
    private final BookReview bookReview;

    public CheckReviewEvent(BookReview bookReview) {
        this.bookReview = bookReview;
    }

    public BookReview getBookReview() {
        return bookReview;
    }
        
}

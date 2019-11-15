package com.bookstore.event;

public class CheckReviewEvent {    
    
    private final String reviewerEmail;

    public CheckReviewEvent(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }
        
}

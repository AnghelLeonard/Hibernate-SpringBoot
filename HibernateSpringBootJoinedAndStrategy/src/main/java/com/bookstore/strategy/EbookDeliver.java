package com.bookstore.strategy;

import com.bookstore.entity.Book;
import com.bookstore.entity.Ebook;
import org.springframework.stereotype.Component;

@Component
public class EbookDeliver implements Delivery<Ebook> {

    @Override
    public void deliver(Ebook book) {
        System.out.println("You can download the book named '" + book.getTitle()
                + "' from the following link: http://bookstore/" + book.getFormat()
                + "/" + book.getTitle());
    }

    @Override
    public Class<? extends Book> ofBook() {
        return Ebook.class;
    }

}

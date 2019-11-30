package com.bookstore.visitor;

import com.bookstore.entity.Ebook;
import com.bookstore.entity.Paperback;
import org.springframework.stereotype.Component;

@Component
public class BookCoverVisitor implements Visitor {

    @Override
    public void visit(Ebook ebook) {
        System.out.println("Applied cover to ebook '" 
                + ebook.getTitle() + "' specific to format " + ebook.getFormat());
    }

    @Override
    public void visit(Paperback paperback) {
        System.out.println("Applied cover to paperback '" 
                + paperback.getTitle() + "' of size " + paperback.getSizeIn());
    }

}

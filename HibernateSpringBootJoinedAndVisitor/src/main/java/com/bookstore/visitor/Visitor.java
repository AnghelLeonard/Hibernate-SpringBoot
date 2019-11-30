package com.bookstore.visitor;

import com.bookstore.entity.Ebook;
import com.bookstore.entity.Paperback;

public interface Visitor {

    void visit(Ebook ebook);

    void visit(Paperback paperback);
}

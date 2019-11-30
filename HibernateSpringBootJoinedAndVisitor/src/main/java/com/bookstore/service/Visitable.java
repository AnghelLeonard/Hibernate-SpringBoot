package com.bookstore.service;

import com.bookstore.visitor.Visitor;

public interface Visitable {

    void accept(Class<? extends Visitor> clazzVisitor);
}

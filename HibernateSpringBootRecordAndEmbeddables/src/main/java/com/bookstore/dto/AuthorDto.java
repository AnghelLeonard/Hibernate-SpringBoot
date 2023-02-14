package com.bookstore.dto;

import com.bookstore.embeddable.Contact;

public record AuthorDto(String name, int age, Contact contact) {}

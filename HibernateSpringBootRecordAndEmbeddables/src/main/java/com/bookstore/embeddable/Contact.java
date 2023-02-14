package com.bookstore.embeddable;

import jakarta.persistence.Embeddable;

@Embeddable
public record Contact(String email, String twitter, String phone) {}

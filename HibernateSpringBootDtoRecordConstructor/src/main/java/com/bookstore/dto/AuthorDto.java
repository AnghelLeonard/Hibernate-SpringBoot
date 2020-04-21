package com.bookstore.dto;

import java.io.Serializable;

public record AuthorDto(String name, int age implements Serializable {}

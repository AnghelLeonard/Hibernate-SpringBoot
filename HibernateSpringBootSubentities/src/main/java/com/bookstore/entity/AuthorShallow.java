package com.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class AuthorShallow extends BaseAuthor {    
}

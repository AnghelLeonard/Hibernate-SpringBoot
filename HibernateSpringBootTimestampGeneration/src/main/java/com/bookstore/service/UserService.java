package com.bookstore.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getCurrentUserName() {
        return "mark1990"; // use Spring Security to retrive the current user
    }
}

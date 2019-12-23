package com.bookstore.dto;

import java.io.Serializable;

public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final String nickname;
    private final int age;

    public AuthorDto(String nickname, int age) {
        this.nickname = nickname;
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "AuthorDto{" + "nickname=" + nickname + ", age=" + age + '}';
    }

}

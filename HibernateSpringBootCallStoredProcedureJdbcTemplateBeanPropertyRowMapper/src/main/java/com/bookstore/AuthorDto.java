package com.bookstore;

import java.io.Serializable;

public class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private int age;

    public AuthorDto() {
    }

    public AuthorDto(String nickname, int age) {
        this.nickname = nickname;
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "AuthorDto{" + "nickname=" + nickname + ", age=" + age + '}';
    }
    
}

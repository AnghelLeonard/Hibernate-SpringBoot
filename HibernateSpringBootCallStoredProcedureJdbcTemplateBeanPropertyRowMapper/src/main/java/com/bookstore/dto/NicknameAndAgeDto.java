package com.bookstore.dto;

import java.io.Serializable;

public class NicknameAndAgeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private int age;

    public NicknameAndAgeDto() {
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

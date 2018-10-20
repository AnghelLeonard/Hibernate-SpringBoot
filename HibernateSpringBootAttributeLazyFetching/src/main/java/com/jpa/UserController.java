package com.jpa;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/new")
    public String newUser() throws IOException {

        userService.newUser();

        return "ok";
    }

    @RequestMapping("/user")
    public User fetchUser() {

        User user = userService.fetchUserWithoutAvatar();
        user.setAvatar(new byte[0]);
        
        return user;
    }

    @RequestMapping("/avatar")
    public User fetchAvatar() {

        return userService.fetchUserWithAvatar();
    }
}

package com.bookstore.by;

import com.bookstore.service.UserService;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

public class ByValueGenerator implements ValueGenerator<String> {

    public final UserService userService;

    public ByValueGenerator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String generateValue(Session session, Object entity) {
        // Hook into a service to get the current user, etc.               
        return userService.getCurrentUserName();
    }
}

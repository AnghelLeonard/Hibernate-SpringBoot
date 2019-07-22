package com.bookstore.modifiedby;

import com.bookstore.service.UserService;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

public class ModifiedByValueGenerator implements ValueGenerator<String> {

    public final UserService userService;

    public ModifiedByValueGenerator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String generateValue(Session session, Object entity) {
        // Hook into a service to get the current user, etc.               
        return userService.getCurrentUserName();
    }
}

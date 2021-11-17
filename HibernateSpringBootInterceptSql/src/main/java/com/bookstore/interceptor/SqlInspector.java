package com.bookstore.interceptor;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class SqlInspector implements StatementInspector {

    @Override
    public String inspect(String sql) {

        System.out.println("The generated SQL is: " + sql);

        // you can modify the sql here or return null to return the same sql
        
        return null;
    }
}

package com.chungco.rest.boxnet.service;

import java.util.ResourceBundle;

public class MockLoginPassword {

    private final String username;

    private final String password;

    public MockLoginPassword() {

        final ResourceBundle rb = ResourceBundle.getBundle("login");

        username = rb.getString("login");

        password = rb.getString("password");

    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }

}

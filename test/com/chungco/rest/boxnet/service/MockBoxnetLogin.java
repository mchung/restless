
package com.chungco.rest.boxnet.service;

import java.util.ResourceBundle;

public class MockBoxnetLogin {

    private final String username;

    private final String password;

    public MockBoxnetLogin() {

        final ResourceBundle rb = ResourceBundle.getBundle("testbox");

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

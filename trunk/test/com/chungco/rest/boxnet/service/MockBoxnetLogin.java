package com.chungco.rest.boxnet.service;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MockBoxnetLogin {

    private final String username;

    private final String password;

    public MockBoxnetLogin() {

        try {
            final ResourceBundle rb = ResourceBundle.getBundle("testbox");

            username = rb.getString("login");

            password = rb.getString("password");
        } catch (MissingResourceException mre) {
            throw new IllegalArgumentException("Did you rename testbox.properties.sample to testbox.properties?");
        }

    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }

}

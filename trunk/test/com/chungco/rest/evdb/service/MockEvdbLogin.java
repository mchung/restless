
package com.chungco.rest.evdb.service;

import java.util.ResourceBundle;

public class MockEvdbLogin {

    private final String username;

    private final String password;

    private final String appkey;

    public MockEvdbLogin() {

        final ResourceBundle rb = ResourceBundle.getBundle("testevdb");

        username = rb.getString("login");

        password = rb.getString("password");

        appkey = rb.getString("appkey");

    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }

    public String getAppKey() {

        return appkey;
    }

}

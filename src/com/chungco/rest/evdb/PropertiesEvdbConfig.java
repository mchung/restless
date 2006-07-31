
package com.chungco.rest.evdb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class PropertiesEvdbConfig implements IEvdbConfig {

    private final String        mHostName;

    private final static String HOST = "host";

    public PropertiesEvdbConfig() {

        final ResourceBundle rb = ResourceBundle.getBundle("com.chungco.rest.evdb.host");

        try {
            mHostName = new URL(rb.getString(HOST)).toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("BoxNetHost is malformed: " + e.getMessage());
        }

    }

    public String getHostName() {

        return mHostName;
    }

}

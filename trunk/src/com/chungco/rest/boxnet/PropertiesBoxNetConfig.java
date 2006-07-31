package com.chungco.rest.boxnet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class PropertiesBoxNetConfig implements IBoxConfig {

    private final static String PROPERTIES = "box.properties";

    private final static String BoxNetHost = "BoxNetHost";

    private final String        mBoxNetHost;

    public PropertiesBoxNetConfig() {

        final Properties props = new Properties();

        try {
            final InputStream fis = getClass().getResourceAsStream(PROPERTIES);
            props.load(fis);
            mBoxNetHost = new URL(props.getProperty(BoxNetHost)).toString();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("BoxNetHost is malformed: " + e.getMessage());
        } catch (final IOException ioe) {
            throw new IllegalArgumentException("I/O error: " + ioe.getMessage());
        }

    }

    public String getBoxNetHost() {

        return mBoxNetHost;
    }

}

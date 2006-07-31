
package com.chungco.rest.evdb;

import com.chungco.rest.ILoginProvider;

public class EvdbLoginProvider implements ILoginProvider {

    private final String mUsername;

    private final String mPassword;

    private final String mAPIKey;

    private final String mUserKey;

    public EvdbLoginProvider(final String pAuthenticationKey, final String pUsername, final String pPassword, final String pUserKey) {

        mAPIKey = pAuthenticationKey;
        mUsername = pUsername;
        mPassword = pPassword;
        mUserKey = pUserKey;

    }

    public String getUsername() {

        return mUsername;
    }

    public String getPassword() {

        return mPassword;
    }

    public String getAppKey() {

        return mAPIKey;
    }

    public String getUserKey() {

        return mUserKey;
    }

}

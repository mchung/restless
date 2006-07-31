package com.chungco.rest;

import com.chungco.rest.exception.RestRuntimeException;

/**
 * @author Marc Chung <mchung@gmail.com>
 * @param <T>
 */
public abstract class AbstractServiceFactory<T extends ILoginProvider> {

    protected T mLoginCredentials;

    public void setCredentials(final T mProvider) {

        mLoginCredentials = mProvider;
    }

    public T getCredentials() {

        if (mLoginCredentials == null) {
            throw new RestRuntimeException("Please login with the proper credentials");
        }
        return mLoginCredentials;
    }

}

package com.chungco.rest.boxnet.exception;

import com.chungco.rest.exception.RestCommandException;

public class BoxException extends RestCommandException {

    /**
     * 
     */
    private static final long serialVersionUID = -6551220923627793737L;

    public BoxException(final String pMessage) {

        super(pMessage);
    }

    public BoxException(final String pMessage, final Throwable pThrowable) {

        super(pMessage, pThrowable);
    }

    public BoxException(final Throwable pThrowable) {

        super(pThrowable);
    }

}

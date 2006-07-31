package com.chungco.rest.exception;

public class MalformedXmlException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -8355142434481486280L;

    public MalformedXmlException(final String pMessage) {

        super(pMessage);

    }

    public MalformedXmlException(final String pMessage, final Throwable pThrowable) {

        super(pMessage, pThrowable);

    }

    public MalformedXmlException(final Throwable pThrowable) {

        super(pThrowable);

    }

}

package com.chungco.rest.exception;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public class RestRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1959614777613835668L;

    public RestRuntimeException() {

    }

    public RestRuntimeException(String pMessage) {

        super(pMessage);
    }

    public RestRuntimeException(String pMessage, Throwable pThrowable) {

        super(pMessage, pThrowable);
    }

    public RestRuntimeException(Throwable pThrowable) {

        super(pThrowable);
    }
}

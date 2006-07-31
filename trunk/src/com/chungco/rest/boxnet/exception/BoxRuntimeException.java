package com.chungco.rest.boxnet.exception;

import com.chungco.rest.exception.RestRuntimeException;

public class BoxRuntimeException extends RestRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 3368716868375664150L;

    public BoxRuntimeException() {

    }

    public BoxRuntimeException(String pMessage) {

        super(pMessage);
    }

    public BoxRuntimeException(String pMessage, Throwable pThrowable) {

        super(pMessage, pThrowable);
    }

    public BoxRuntimeException(Throwable pThrowable) {

        super(pThrowable);
    }

}

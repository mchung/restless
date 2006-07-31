package com.chungco.rest.exception;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public class RestCommandException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 6085997932512656583L;

    public RestCommandException(String pMessage) {

        super(pMessage);
    }

    public RestCommandException(String pMessage, Throwable pThrowable) {

        super(pMessage, pThrowable);
    }

    public RestCommandException(Throwable pThrowable) {

        super(pThrowable);
    }

}

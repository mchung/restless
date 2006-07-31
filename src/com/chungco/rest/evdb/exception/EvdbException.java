
package com.chungco.rest.evdb.exception;

import com.chungco.rest.exception.RestCommandException;

public class EvdbException extends RestCommandException {

    /**
     * 
     */
    private static final long serialVersionUID = 7726866712659619348L;

    public EvdbException(final String pMessage) {

        super(pMessage);
    }

    public EvdbException(final String pMessage, final Throwable pThrowable) {

        super(pMessage, pThrowable);
    }

    public EvdbException(final Throwable pThrowable) {

        super(pThrowable);
    }

}

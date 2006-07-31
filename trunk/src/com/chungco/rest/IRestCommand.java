
package com.chungco.rest;

import com.chungco.rest.exception.RestCommandException;

/**
 * A RESTful Command Object.
 * 
 * @author Marc Chung <mchung@gmail.com>
 * @param <O>
 *            A generic return type
 */
public interface IRestCommand<R extends IRestResult> {

    /**
     * The execute method of the Command (GoF) design pattern. Implementations
     * of this method should make every attempt to be interruptible.
     * 
     * @return A result set
     * @throws InterruptedException
     *             REST operations implemented by this API shall be
     *             interruptible
     * @throws RestCommandException
     *             If the IRestCommand threw an exception
     */
    public R execute() throws InterruptedException, RestCommandException;

}

package com.chungco.core;

/**
 * Pointer to a function
 * 
 * @author Marc Chung <mchung@gmail.com>
 * @param <X>
 */
public interface IClosure<X> {

    public void apply(X px);

}

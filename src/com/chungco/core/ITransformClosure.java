package com.chungco.core;

/**
 * Pointer to a function that also transforms the output
 * 
 * @author Marc Chung <mchung@gmail.com>
 * @param <From>
 * @param <To>
 */
public interface ITransformClosure<From, To> {

    To apply(From p);

}

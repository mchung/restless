package com.chungco.rest;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public interface IXmlResult {

    /**
     * Implementations are responsible for producing an XML representation of
     * the object
     * 
     * @return An XML String representing the Object
     */
    public String toXml();

}

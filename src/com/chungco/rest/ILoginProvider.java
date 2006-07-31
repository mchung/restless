package com.chungco.rest;

/**
 * Encapsulates the data required for a typical login, username and password
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public interface ILoginProvider {

    public String getUsername();

    public String getPassword();

}

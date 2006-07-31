package com.chungco.rest;

public abstract class RestServiceFactory {

    public abstract IRestCommand createRestCommand(final Class clazz);

}

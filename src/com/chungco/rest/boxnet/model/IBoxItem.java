package com.chungco.rest.boxnet.model;

import com.chungco.rest.IXmlResult;

public interface IBoxItem extends IXmlResult {

    public String getId();

    public String getName();

    public void setName(final String pName);

}

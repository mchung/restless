package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;

public class RenameItemResult extends AbstractBoxResult implements IXmlResult {

    public final static String ERROR   = "error_rename";

    public final static String SUCCESS = "successful_rename";

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus());
    }

    public String toXml() {

        return toStatusXml(getStatus(), null);
    }

}

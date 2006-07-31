package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;

public class DeleteItemResult extends AbstractBoxResult implements IXmlResult {

    public final static String ERROR   = "error_delete";

    public final static String SUCCESS = "successful_delete";

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus());
    }

    public String toXml() {

        return toStatusXml(getStatus(), null);
    }

}

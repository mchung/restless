package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;

public class MoveItemResult extends AbstractBoxResult implements IXmlResult {

    public final static String ERROR   = "error_move";

    public final static String SUCCESS = "successful_move";

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus());
    }

    public String toXml() {

        return toStatusXml(getStatus(), null);
    }
}

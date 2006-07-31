package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;
import com.chungco.rest.boxnet.model.BoxFolder;

public class FileListingResult extends AbstractBoxResult implements IXmlResult {

    private BoxFolder             mFolder = null;

    protected final static String SUCCESS = "listing_ok";

    public void setFolder(final BoxFolder pFolder) {

        mFolder = pFolder;

    }

    public BoxFolder getFolder() {

        return mFolder;
    }

    public String toXml() {

        StringBuilder sb = new StringBuilder();

        sb.append("<xml>");
        sb.append("<" + XML_STATUS + ">");
        sb.append(getStatus());
        sb.append("</" + XML_STATUS + ">");

        sb.append(getFolder().toXml());
        sb.append("</xml>");

        return sb.toString();
    }

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus());
    }

}
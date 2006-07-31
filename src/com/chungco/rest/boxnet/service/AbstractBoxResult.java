package com.chungco.rest.boxnet.service;

import com.chungco.rest.AbstractRestResult;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public abstract class AbstractBoxResult extends AbstractRestResult {

    protected final static String XML_STATUS = "status";

    private String                mStatus;

    private String                mXmlResponse;

    public AbstractBoxResult() {

        mStatus = "";
    }

    public String getStatus() {

        return mStatus;
    }

    public String getXmlResponse() {

        return mXmlResponse;
    }

    public void setXmlResponse(final String pXmlResponse) {

        mXmlResponse = pXmlResponse;
    }

    public void setStatus(final String status) {

        mStatus = status;
    }

    public final static String toStatusXml(final String pStatus, final String pDesc) {

        final StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<status>");
        sb.append(pStatus);
        sb.append("</status>");
        if (pDesc != null) {
            sb.append("<description>");
            sb.append(pDesc);
            sb.append("</description>");
        }
        sb.append("</xml>");
        return sb.toString();
    }

}

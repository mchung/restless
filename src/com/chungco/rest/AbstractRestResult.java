
package com.chungco.rest;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An abstract result type for services
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public abstract class AbstractRestResult implements IRestResult {

    /** Did the service execute properly */
    public abstract Boolean success();

    /** The raw results */
    private String mXmlResponse;

    public String getXmlResponse() {

        return mXmlResponse;
    }

    public void setXmlResponse(final String pXmlResponse) {

        mXmlResponse = pXmlResponse;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);

    }

}

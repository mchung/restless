
package com.chungco.rest;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An abstract result type for services
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public abstract class AbstractRestResult implements IRestResult {

    public abstract Boolean success();

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);

    }

}

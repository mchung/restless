package com.chungco.core.xml;

import java.util.List;

import com.chungco.core.KeyValuePair;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public class XmlElement extends KeyValuePair<String, List<XmlAttr>> {

    /**
     * A KeyValue pair consisting of a String (the name of the tag) and a List
     * of {@link XmlAttr} objects containing name=value pairs for attributes.
     * 
     * @param pK
     *            A String containing the name of the tag
     * @param pV
     *            A List of type {@link XmlAttr} which maps attribute names to
     *            attribute values
     */
    public XmlElement(final String pK, final List<XmlAttr> pV) {

        super(pK, pV);
    }

}

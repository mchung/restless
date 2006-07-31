package com.chungco.core.xml;

import com.chungco.core.KeyValuePair;

/**
 * For the attribute name=value pairs in an XML Element
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public class XmlAttr extends KeyValuePair<String, String> {

    public XmlAttr(String pK, String pV) {

        super(pK, pV);
    }

}

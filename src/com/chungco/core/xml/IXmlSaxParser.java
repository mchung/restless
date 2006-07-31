package com.chungco.core.xml;

import com.chungco.core.Stack;

/**
 * Implementations will parse XML
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public interface IXmlSaxParser {

    /**
     * Called when the XML parser encounters the beginning of an element. ie.
     * <start> or <end>
     * 
     * @param pEltAttrPair
     * @param pParents
     */
    public void startElement(final XmlElement pEltAttrPair, final Stack<XmlElement> pParents);

    /**
     * Called when the XML parser encounters the ending of an element. ie.
     * </start> or </end>
     * 
     * @param pEltAttrPair
     * @param pParents
     */
    public void endElement(final XmlElement pEltAttrPair, final Stack<XmlElement> pParents);

    /**
     * Called when the XML parser encounters the value within an element. ie.
     * the "string" in <name>string</name>
     * 
     * @param pEltAttrPair
     * @param pParents
     */
    public void startContent(final XmlElement pEltAttrPair, final String pXmlText, final Stack<XmlElement> pParents);

    /**
     * Called when the XML parser encounters the beginning of the document
     */
    public void startDocument();

}

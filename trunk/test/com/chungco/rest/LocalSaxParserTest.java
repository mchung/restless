
package com.chungco.rest;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.chungco.core.Stack;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.RestUtils;

// Test that my XML parsing routines are good
// This is not a Real Test Case yet
public class LocalSaxParserTest extends TestCase {

    public void testXmlLogic1() {

        final String pFilename = "TestXml1.xml";
        parse(pFilename, getParser());
    }

    public void testXmlLogic2() {

        final String pFilename = "TestXml2.xml";
        parse(pFilename, getParser());
    }

    public void parse(final String fName, final IXmlSaxParser fParser) {

        try {
            System.out.println("\n\nParsing: " + fName);
            RestUtils.parseXml2(RestUtils.getXmlFromResource(this, fName), fParser);
        } catch (Throwable t) {
            Assert.fail(t.getMessage());
        }
    }

    public IXmlSaxParser getParser() {

        return new IXmlSaxParser() {

            public void startDocument() {

                System.out.println("Document");

            }

            public void startElement(final XmlElement pXmlElt, final Stack<XmlElement> pParents) {

                System.out.println(" S Tags: " + pXmlElt);
            };

            public void startContent(final XmlElement pXmlElt, final String pXmlText, final Stack<XmlElement> pParents) {

                System.out.println("  Tag: " + pXmlElt + " Text: " + pXmlText + " Parent: " + pParents);
            }

            public void endElement(final XmlElement pXmlElt, Stack<XmlElement> pParents) {

                System.out.println(" E Tags: " + pXmlElt + " parent: " + pParents);

            }

        };

    }

}

package com.chungco.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javolution.xml.pull.XmlPullParser;
import javolution.xml.pull.XmlPullParserException;
import javolution.xml.pull.XmlPullParserImpl;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.chungco.core.Stack;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlAttr;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.exception.MalformedXmlException;
import com.chungco.rest.exception.RestRuntimeException;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public final class RestUtils {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public final static void parseXml2(final String pFilename, final IXmlSaxParser pC) throws MalformedXmlException {

        final XmlPullParserImpl xpp = new XmlPullParserImpl();

        try {
            xpp.setInput(new StringReader(pFilename));
            int eventType = xpp.getEventType();

            final Stack<XmlElement> tags = new Stack<XmlElement>();
            while (eventType != -1) {

                if (eventType == XmlPullParser.START_DOCUMENT) {

                    pC.startDocument();

                } else if (eventType == XmlPullParser.END_DOCUMENT) {

                    // final XmlElementAttrPair tag = parseElementAttr(xpp);
                    // pC.endDocument(tag);
                    eventType = -1;
                    continue;

                } else if (eventType == XmlPullParser.START_TAG) {

                    final XmlElement tag = parseElementAttr(xpp);
                    tags.push(tag);
                    pC.startElement(tag, tags.cdr());

                } else if (eventType == XmlPullParser.END_TAG) {

                    final XmlElement tag = parseElementAttr(xpp);
                    pC.endElement(tag, tags.cdr());
                    tags.pop();

                } else if (eventType == XmlPullParser.TEXT) {

                    final String value = xpp.getText().toString().trim();
                    if (value.length() > 0) {
                        pC.startContent(tags.peek(), value, tags.cdr());
                    }

                }

                eventType = xpp.next();
            }
        } catch (final XmlPullParserException e) {
            throw new MalformedXmlException("Malformed XML: ", e);
        } catch (final IOException e) {
            throw new MalformedXmlException("Bad I/O: ", e);
        }

        // Assert.assertEquals(0, tags.size());

    }

    private static XmlElement parseElementAttr(final XmlPullParserImpl xpp) {

        final String cs = xpp.getName().toString();
        final List<XmlAttr> attrList = new ArrayList<XmlAttr>();
        for (int i = 0, j = xpp.getAttributeCount(); i < j; i++) {
            attrList.add(new XmlAttr(xpp.getAttributeName(i).toString(), xpp.getAttributeValue(i).toString()));
        }
        final XmlElement tag = new XmlElement(cs, attrList);
        return tag;
    }

    /**
     * @param pResourcePath
     *            The name of the resource
     * @return The contents of the resource
     */
    public final static String getXmlFromResource(final Object pCaller, final String pResourcePath) {

        final InputStream is = pCaller.getClass().getResourceAsStream(pResourcePath);
        String xml;
        try {
            // byte[] buf = StreamUtil.readInputStream(is);
            // new String(buf);//, StreamUtil.UTF_8_CHARSET);
            xml = streamToString(is);
        } catch (IOException e) {
            xml = "";
            throw new RestRuntimeException(e);
        }
        return xml;
    }

    public final static String streamToString(final InputStream pIs) throws IOException {
        return IOUtils.toString(pIs);
    }
    
    public final static String toString(final Object pObj) {
        return ToStringBuilder.reflectionToString(pObj, ToStringStyle.SIMPLE_STYLE);
    }

    public final static void writeStringToStream(final String pStr, final OutputStream pOs) throws IOException {
        IOUtils.write(pStr, pOs);
    }
    
    public final static void closeQuietly(final OutputStream pOs) {
        IOUtils.closeQuietly(pOs);
    }

    public final static void closeQuietly(final InputStream pIs) {
        IOUtils.closeQuietly(pIs);
    }

    
    public final static String en(final String pK) {

        return "@" + pK + "@";
    }

    // Returns a single alphanumeric word, possibly seperated with periods,
    // between two "@" symbols
    private final static String REGEXP = "\\@[A-Za-z0-9._]*\\@";

    /**
     * See test case {@link RegexpTest}
     * 
     * @return
     */
    public final static String getEnRE() {

        return REGEXP;
    }

}

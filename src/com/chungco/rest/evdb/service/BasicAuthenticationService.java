
package com.chungco.rest.evdb.service;

import java.util.List;

import com.chungco.core.Stack;
import com.chungco.core.xml.IXmlSaxAdapter;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlAttr;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.RestUtils;
import com.chungco.rest.exception.MalformedXmlException;

public class BasicAuthenticationService extends AbstractEvdbService<AuthenticationResult> {

    @Override
    public String getEndpointURL() {

        return getEvdbConfig().getHostName() + "/rest/users/login?" + makeGET(KEY_APP_KEY, KEY_USERNAME, KEY_PASSWORD);
    }

    @Override
    protected AuthenticationResult doParseXml(final String pXmlStr) throws MalformedXmlException {

        final AuthenticationResult result = new AuthenticationResult();

        final IXmlSaxParser populate = new IXmlSaxAdapter() {

            public void startElement(final XmlElement pTagAttrPair, final Stack<XmlElement> pParents) {

                final String key = pTagAttrPair.key();
                if (key.equals(AuthenticationResult.XML_ERROR_KEY)) {
                    final List<XmlAttr> xmlAttrList = pTagAttrPair.value();
                    for (XmlAttr attr : xmlAttrList) {
                        if (attr.key().equals(AuthenticationResult.XML_ERROR_STRING)) {
                            result.setErrorString(attr.value());
                        }
                    }
                }
            }

            public void startContent(final XmlElement kp, final String pXmlText, final Stack<XmlElement> pParents) {

                final String key = kp.key();
                if (key.equals(AuthenticationResult.XML_USER_KEY)) {
                    result.setUserKey(pXmlText);
                } else if (key.equals(AuthenticationResult.XML_ERROR_DESC)) {
                    result.setErrorDesc(pXmlText);
                } else if (key.equals(AuthenticationResult.XML_NONCE_KEY)) {
                    result.setNonce(pXmlText);
                }

            }
        };

        try {
            RestUtils.parseXml2(pXmlStr, populate);
        } catch (MalformedXmlException mxe) {
            throw new MalformedXmlException("Couldn't authenticate against Eventful.com with that username/password.", mxe.getCause());
        }
        return result;
    }

}

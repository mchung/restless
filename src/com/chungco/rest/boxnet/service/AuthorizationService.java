package com.chungco.rest.boxnet.service;

import com.chungco.core.Stack;
import com.chungco.core.xml.IXmlSaxAdapter;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.RestUtils;
import com.chungco.rest.exception.MalformedXmlException;

public class AuthorizationService extends AbstractBoxService<AuthorizationResult> {

    protected final static String KEY_INPUT_USERNAME = "login_here";

    protected final static String KEY_INPUT_PASSWORD = "password_here";

    protected final static String RESOURCE           = "AuthorizationService.xml";

    @Override
    public String getEndpointURL() {

        return getBoxConfig().getBoxNetHost() + "/ping";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AuthorizationResult doParseXml(final String pXmlStr) throws MalformedXmlException {

        final AuthorizationResult result = new AuthorizationResult();

        final IXmlSaxParser populate = new IXmlSaxAdapter() {

            public void startContent(final XmlElement kp, final String pXmlText, final Stack<XmlElement> pParents) {

                final String xmltag = kp.key();
                if (xmltag.equals(AuthorizationResult.XML_STATUS)) {
                    result.setStatus(pXmlText);
                } else if (xmltag.equals(AuthorizationResult.XML_SID)) {
                    result.setSID(pXmlText);
                } else if (xmltag.equals(AuthorizationResult.XML_LOGIN)) {
                    result.setLogin(pXmlText);
                } else if (xmltag.equals(AuthorizationResult.XML_EMAIL)) {
                    result.setEmail(pXmlText);
                } else if (xmltag.equals(AuthorizationResult.XML_ACCESS_ID)) {
                    result.setAccessId(pXmlText);
                } else if (xmltag.equals(AuthorizationResult.XML_USER_ID)) {
                    result.setUserId(pXmlText);
                } else if (xmltag.equals(AuthorizationResult.XML_SPACE_AMOUNT)) {
                    result.setSpaceAmount(Long.parseLong(pXmlText));
                } else if (xmltag.equals(AuthorizationResult.XML_SPACE_USED)) {
                    result.setSpaceUsed(Long.parseLong(pXmlText));
                }

            }
        };

        try {
            RestUtils.parseXml2(pXmlStr, populate);
        } catch (MalformedXmlException mxe) {
            throw new MalformedXmlException("Couldn't login to Box.net with that username/password.", mxe.getCause());
        }

        return result;
    }

    @Override
    protected String loadXml() {

        return RestUtils.getXmlFromResource(this, RESOURCE);
    }

    public void setLogin(final String pUsername, final String pPassword) {

        setParam(KEY_INPUT_USERNAME, pUsername);
        setParam(KEY_INPUT_PASSWORD, pPassword);
    }

}

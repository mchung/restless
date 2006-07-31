package com.chungco.rest.boxnet.service;

import com.chungco.core.Stack;
import com.chungco.core.xml.IXmlSaxAdapter;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.RestUtils;
import com.chungco.rest.boxnet.model.Target;
import com.chungco.rest.exception.MalformedXmlException;

public class MoveItemService extends AbstractBoxService<MoveItemResult> {

    protected final static String RESOURCE           = "MoveItemService.xml";

    public final static String    KEY_ITEM_TYPE      = "target_type";

    public final static String    KEY_ITEM_ID        = "item_id";

    public final static String    KEY_DESTINATION_ID = "dest_id";

    @Override
    public String getEndpointURL() {

        final String sid = getParam(KEY_SID);
        return getBoxConfig().getBoxNetHost() + "/ping/" + sid;
    }

    @Override
    protected MoveItemResult doParseXml(final String pXmlStr) throws MalformedXmlException {

        final MoveItemResult result = new MoveItemResult();

        final IXmlSaxParser sax2 = new IXmlSaxAdapter() {

            public void startContent(final XmlElement kp, final String pXmlText, final Stack<XmlElement> pParents) {

                final String xmltag = kp.key();
                if (xmltag.equals(MoveItemResult.XML_STATUS)) {
                    result.setStatus(pXmlText);
                }
            };
        };

        try {
            RestUtils.parseXml2(pXmlStr, sax2);
        } catch (MalformedXmlException mxe) {
            throw new MalformedXmlException("Couldn't move item.", mxe.getCause());
        }
        return result;
    }

    @Override
    protected String loadXml() {

        return RestUtils.getXmlFromResource(this, RESOURCE);
    }

    public void setTargetType(final Target pType) {

        setParam(KEY_ITEM_TYPE, pType.toString());
    }

    public void setItem(final String pItemId) {

        setParam(KEY_ITEM_ID, pItemId);
    }

    public void setDestinationFolder(final String pFolderId) {

        setParam(KEY_DESTINATION_ID, pFolderId);
    }

}

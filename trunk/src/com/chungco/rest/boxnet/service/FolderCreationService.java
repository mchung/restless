package com.chungco.rest.boxnet.service;

import com.chungco.core.Stack;
import com.chungco.core.xml.IXmlSaxAdapter;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.RestUtils;
import com.chungco.rest.boxnet.model.SharingOpts;
import com.chungco.rest.exception.MalformedXmlException;

public class FolderCreationService extends AbstractBoxService<FolderCreationResult> {

    protected final static String RESOURCE             = "FolderCreationService.xml";

    public final static String    KEY_PARENT_FOLDER_ID = "parent_folder";

    public final static String    KEY_FOLDER_NAME      = "folder_name";

    @Override
    public String getEndpointURL() {

        final String sid = getParam(KEY_SID);
        return getBoxConfig().getBoxNetHost() + "/ping/" + sid;
    }

    @Override
    public String loadXml() {

        return RestUtils.getXmlFromResource(this, RESOURCE);
    }

    @Override
    protected FolderCreationResult doParseXml(final String pXmlStr) throws MalformedXmlException {

        final FolderCreationResult result = new FolderCreationResult();

        final IXmlSaxParser populate = new IXmlSaxAdapter() {

            public void startContent(final XmlElement kp, final String pXmlText, final Stack<XmlElement> pParents) {

                final String xmltag = kp.key();
                if (xmltag.equals(FolderCreationResult.XML_STATUS)) {
                    result.setStatus(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_FOLDER_ID)) {
                    result.setFolderId(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_FOLDER_NAME)) {
                    result.setFolderName(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_FOLDER_TYPE_ID)) {
                    result.setFolderTypeId(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_USER_ID)) {
                    result.setUserId(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_PATH)) {
                    result.setPath(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_SHARED)) {
                    result.setShared(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_SHOW_COMMENTS)) {
                    result.setShowComments(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_PARENT_FOLDER_ID)) {
                    result.setParentFolderId(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_PASSWORD)) {
                    result.setPassword(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_SHARED_NAME)) {
                    result.setSharedName(pXmlText);
                } else if (xmltag.equals(FolderCreationResult.XML_DATE_CREATED)) {
                    result.setDateCreated(Long.parseLong(pXmlText));
                } else if (xmltag.equals(FolderCreationResult.XML_DATE_UPDATED)) {
                    result.setDateUpdated(Long.parseLong(pXmlText));
                }
            };
        };

        try {
            RestUtils.parseXml2(pXmlStr, populate);
        } catch (MalformedXmlException mxe) {
            throw new MalformedXmlException("Couldn't create folder.", mxe.getCause());
        }

        return result;

    }

    public void setParentFolderId(final String pId) {

        setParam(FolderCreationService.KEY_PARENT_FOLDER_ID, pId);
    }

    public void setFolderName(final String pName) {

        setParam(FolderCreationService.KEY_FOLDER_NAME, pName);
    }

    public void setSharingOptions(final SharingOpts pSharingOpts) {

        setParam(SharingOpts.KEY_FOLDER_SHARE, pSharingOpts.getShareType().toString());
        setParam(SharingOpts.KEY_FOLDER_COMMENTS, pSharingOpts.getComments());
        setParam(SharingOpts.KEY_FOLDER_PASSWORD, pSharingOpts.getPassword());
        setParam(SharingOpts.KEY_FOLDER_SEND_TO, pSharingOpts.getSendEmailTo());
        setParam(SharingOpts.KEY_FOLDER_MESSAGE, pSharingOpts.getEmailMessage());
        setParam(SharingOpts.KEY_FOLDER_PASSWORD, pSharingOpts.getPassword());

    }

}

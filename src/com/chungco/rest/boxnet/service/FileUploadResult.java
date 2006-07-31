package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;
import com.chungco.rest.boxnet.model.BoxFile;

public class FileUploadResult extends AbstractBoxResult implements IXmlResult {

    protected final static String XML_FILE_NAME = "file_name";

    protected final static String XML_SIZE      = "size";

    protected final static String XML_USER_ID   = "user_id";

    protected final static String XML_SHARED    = "shared";

    protected final static String XML_FOLDER_ID = "folder_id";

    protected final static String XML_FILE_ID   = "file_id";

    private final BoxFile         mFile;

    private String                mUserId;

    protected final static String SUCCESS       = "upload_ok";

    public FileUploadResult() {

        mFile = new BoxFile();
    }

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus());
    }

    public String toXml() {

        // TODO Implement toXML for FileUploadResult
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void setName(final String pName) {

        mFile.setName(pName);

    }

    public void setSize(final Long pSize) {

        mFile.setSize(pSize);

    }

    public void setFolderId(final String pFolderId) {

        mFile.setFolderId(pFolderId);
    }

    public void setFileId(final String pFileId) {

        mFile.setId(pFileId);

    }

    public void setShared(final String pShared) {

        mFile.setShared(pShared);
    }

    public BoxFile getFile() {

        return mFile;
    }

    public String getUserId() {

        return mUserId;
    }

    public void setUserId(final String pUserId) {

        mUserId = pUserId;
    }

}

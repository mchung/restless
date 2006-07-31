package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;

public class FolderCreationResult extends AbstractBoxResult implements IXmlResult {

    protected final static String SUCCESS              = "create_ok";

    protected final static String SUCCESS_EXISTS       = "folder_exists";

    protected final static String XML_FOLDER           = "folder";

    // folder_exists, create_ok
    protected final static String XML_FOLDER_ID        = "folder_id";

    // folder_exists, create_ok
    protected final static String XML_FOLDER_NAME      = "folder_name";

    protected final static String XML_FOLDER_TYPE_ID   = "folder_type_id";

    // folder_exists, create_ok
    protected final static String XML_USER_ID          = "user_id";

    protected final static String XML_PATH             = "path";

    // folder_exists, create_ok
    protected final static String XML_SHARED           = "shared";

    protected final static String XML_SHOW_COMMENTS    = "show_comments";

    // folder_exists, create_ok
    protected final static String XML_PARENT_FOLDER_ID = "parent_folder_id";

    // folder_exists, create_ok
    protected final static String XML_PASSWORD         = "password";

    // folder_exists, create_ok
    protected final static String XML_SHARED_NAME      = "shared_name";

    // folder_exists, create_ok
    protected final static String XML_DATE_CREATED     = "created";

    // folder_exists, create_ok
    protected final static String XML_DATE_UPDATED     = "updated";

    // folder_exists: <size> for a folder?, <friend_folder> list? csv? <dscr>
    // description?

    private String                mFolderId;

    private String                mFolderName;

    private String                mFolderTypeId;

    private String                mUserId;

    private String                mPath;

    private String                mShared;

    private String                mShowComments;

    private String                mParentFolderId;

    private String                mPassword;

    private String                mSharedName;

    private Long                  mDateUpdated;

    private Long                  mDateCreated;

    public FolderCreationResult() {

        mFolderId = "";
        mFolderName = "";
        mFolderTypeId = "";
        mPassword = "";
        mPath = "";
        mShared = "";
        mShowComments = "";
        mUserId = "";
        mSharedName = "";
        mDateCreated = 0L;
        mDateUpdated = 0L;

    }

    public String getFolderId() {

        return mFolderId;
    }

    public void setFolderId(final String pFolderId) {

        mFolderId = pFolderId;
    }

    public String getFolderName() {

        return mFolderName;
    }

    public void setFolderName(final String pFolderName) {

        mFolderName = pFolderName;
    }

    public String getFolderTypeId() {

        return mFolderTypeId;
    }

    public void setFolderTypeId(final String pFolderTypeId) {

        mFolderTypeId = pFolderTypeId;
    }

    public String getParentFolderId() {

        return mParentFolderId;
    }

    public void setParentFolderId(final String pParentFolderId) {

        mParentFolderId = pParentFolderId;
    }

    public String getPassword() {

        return mPassword;
    }

    public void setPassword(final String pPassword) {

        mPassword = pPassword;
    }

    public String getPath() {

        return mPath;
    }

    public void setPath(final String pPath) {

        mPath = pPath;
    }

    public String getShared() {

        return mShared;
    }

    public void setShared(final String pShared) {

        mShared = pShared;
    }

    public String getShowComments() {

        return mShowComments;
    }

    public void setShowComments(final String pShowComments) {

        mShowComments = pShowComments;
    }

    public String getUserId() {

        return mUserId;
    }

    public void setUserId(final String pUserId) {

        mUserId = pUserId;
    }

    public String getSharedName() {

        return mSharedName;
    }

    public void setSharedName(final String pSharedName) {

        mSharedName = pSharedName;
    }

    public Long getDateCreated() {

        return mDateCreated;
    }

    public void setDateCreated(final Long pDateCreated) {

        if (pDateCreated == null)
            mDateCreated = 0L;
        else
            mDateCreated = pDateCreated;
    }

    public Long getDateUpdated() {

        return mDateUpdated;
    }

    public void setDateUpdated(final Long pDateUpdated) {

        if (pDateUpdated == null)
            mDateUpdated = 0L;
        else
            mDateUpdated = pDateUpdated;
    }

    public String toXml() {

        final StringBuilder sb = new StringBuilder();

        // XML_SHARED_NAME

        sb.append("<xml>");
        sb.append("<" + XML_STATUS + ">");
        sb.append(getStatus());
        sb.append("</" + XML_STATUS + ">");

        sb.append("<" + XML_FOLDER + ">");
        sb.append("<" + XML_FOLDER_ID + ">");
        sb.append(getFolderId());
        sb.append("</" + XML_FOLDER_ID + ">");
        sb.append("<" + XML_FOLDER_NAME + ">");
        sb.append(getFolderName());
        sb.append("</" + XML_FOLDER_NAME + ">");
        sb.append("<" + XML_USER_ID + ">");
        sb.append(getUserId());
        sb.append("</" + XML_USER_ID + ">");
        // sb.append("<" + XML_PATH + ">");
        // sb.append(getPath());
        // sb.append("</" + XML_PATH + ">");
        sb.append("<" + XML_SHARED + ">");
        sb.append(getShared());
        sb.append("</" + XML_SHARED + ">");
        // sb.append("<" + XML_SHOW_COMMENTS + ">");
        // sb.append(getShowComments());
        // sb.append("</" + XML_SHOW_COMMENTS + ">");
        sb.append("<" + XML_PARENT_FOLDER_ID + ">");
        sb.append(getParentFolderId());
        sb.append("</" + XML_PARENT_FOLDER_ID + ">");
        sb.append("<" + XML_PASSWORD + ">");
        sb.append(getPassword());
        sb.append("</" + XML_PASSWORD + ">");
        sb.append("<" + XML_DATE_CREATED + ">");
        sb.append(getDateCreated());
        sb.append("</" + XML_DATE_CREATED + ">");
        sb.append("<" + XML_DATE_UPDATED + ">");
        sb.append(getDateUpdated());
        sb.append("</" + XML_DATE_UPDATED + ">");
        sb.append("</" + XML_FOLDER + ">");
        sb.append("</xml>");
        return sb.toString();
    }

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus()) || SUCCESS_EXISTS.equals(getStatus());
    }

}

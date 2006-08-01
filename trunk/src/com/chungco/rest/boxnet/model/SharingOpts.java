/**
 * 
 */
package com.chungco.rest.boxnet.model;

public class SharingOpts {

    public final static String KEY_FOLDER_SHARE    = "share";

    public final static String KEY_FOLDER_COMMENTS = "p_comments";

    public final static String KEY_FOLDER_PASSWORD = "a_password";

    public final static String KEY_FOLDER_SEND_TO  = "send_to";

    public final static String KEY_FOLDER_MESSAGE  = "e_message";

    public final static String KEY_TYPE_ID         = "type_id";

    private Share              mShareType;

    private String             mComments;

    private String             mPassword;

    private String             mEmail;

    private String             mMesg;

    private String             mTypeId;

    public SharingOpts(final Share pShareType) {

        mShareType = pShareType;
        mComments = "";
        mPassword = "";
        mEmail = "";
        mMesg = "";
        mTypeId = "";

    }

    public SharingOpts() {

        this(Share.IGNORE);

    }

    public void setShareType(final Share pShare) {

        mShareType = pShare;
    }

    public void setComments(final String pComments) {

        mComments = pComments;
    }

    public void setPassword(final String pPassword) {

        mPassword = pPassword;
    }

    public void setSendEmailTo(final String pSendTo) {

        mEmail = pSendTo;
    }

    public void setEmailMessage(final String pMesg) {

        mMesg = pMesg;
    }

    public void setTypeId(final String pTypeId) {

        mTypeId = pTypeId;
    }

    public String getComments() {

        return mComments;
    }

    public String getSendEmailTo() {

        return mEmail;
    }

    public String getEmailMessage() {

        return mMesg;
    }

    public String getPassword() {

        return mPassword;
    }

    public Share getShareType() {

        return mShareType;
    }

    public String getTypeId() {

        return mTypeId;
    }

}
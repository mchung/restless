package com.chungco.rest.boxnet.service;

import com.chungco.rest.IXmlResult;

public class AuthorizationResult extends AbstractBoxResult implements IXmlResult {

    protected final static String XML_USER         = "user";

    protected final static String XML_SID          = "sid";

    protected final static String XML_LOGIN        = "login";

    protected final static String XML_EMAIL        = "email";

    protected final static String XML_ACCESS_ID    = "access_id";

    protected final static String XML_USER_ID      = "user_id";

    protected final static String XML_SPACE_AMOUNT = "space_amount";

    protected final static String XML_SPACE_USED   = "space_used";

    protected final static String SUCCESS          = "logged";

    public final static String    INVALID_LOGIN    = "invalid_login";

    private String                mSID;

    private String                mLogin;

    private String                mEmail;

    private String                mAccessId;

    private String                mUserId;

    private Long                  mSpaceAmount     = 0L;

    private Long                  mSpaceUsed       = 0L;

    public AuthorizationResult() {

        mSID = "";
        mLogin = "";
        mEmail = "";
        mAccessId = "";
        mUserId = "";
        mSpaceAmount = 0L;
        mSpaceUsed = 0L;
    }

    public String getAccessId() {

        return mAccessId;
    }

    public void setAccessId(final String accessId) {

        mAccessId = accessId;
    }

    public String getEmail() {

        return mEmail;
    }

    public void setEmail(final String email) {

        mEmail = email;
    }

    public String getLogin() {

        return mLogin;
    }

    public void setLogin(final String login) {

        mLogin = login;
    }

    public String getSID() {

        return mSID;
    }

    public void setSID(final String msid) {

        mSID = msid;
    }

    public Long getSpaceAmount() {

        return mSpaceAmount;
    }

    public void setSpaceAmount(final Long spaceAmount) {

        if (spaceAmount == null)
            mSpaceAmount = 0L;
        else
            mSpaceAmount = spaceAmount;
    }

    public Long getSpaceUsed() {

        return mSpaceUsed;
    }

    public void setSpaceUsed(final Long spaceUsed) {

        if (spaceUsed == null)
            mSpaceUsed = 0L;
        else
            mSpaceUsed = spaceUsed;
    }

    public String getUserId() {

        return mUserId;
    }

    public void setUserId(final String userId) {

        mUserId = userId;
    }

    @Override
    public Boolean success() {

        return SUCCESS.equals(getStatus());
    }

    public String toXml() {

        final StringBuilder sb = new StringBuilder();

        sb.append("<xml>");
        sb.append("<" + XML_STATUS + ">");
        sb.append(getStatus());
        sb.append("</" + XML_STATUS + ">");

        sb.append("<" + XML_USER + ">");
        sb.append("<" + XML_SID + ">");
        sb.append(getSID());
        sb.append("</" + XML_SID + ">");
        sb.append("<" + XML_LOGIN + ">");
        sb.append(getLogin());
        sb.append("</" + XML_LOGIN + ">");
        sb.append("<" + XML_EMAIL + ">");
        sb.append(getEmail());
        sb.append("</" + XML_EMAIL + ">");
        sb.append("<" + XML_ACCESS_ID + ">");
        sb.append(getAccessId());
        sb.append("</" + XML_ACCESS_ID + ">");
        sb.append("<" + XML_USER_ID + ">");
        sb.append(getUserId());
        sb.append("</" + XML_USER_ID + ">");
        sb.append("<" + XML_SPACE_AMOUNT + ">");
        sb.append(getUserId());
        sb.append("</" + XML_SPACE_AMOUNT + ">");
        sb.append("<" + XML_SPACE_USED + ">");
        sb.append(getSpaceUsed());
        sb.append("</" + XML_SPACE_USED + ">");
        sb.append("</" + XML_USER + ">");
        sb.append("</xml>");
        return sb.toString();

    }
}

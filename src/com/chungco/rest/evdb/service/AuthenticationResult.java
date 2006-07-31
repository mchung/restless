
package com.chungco.rest.evdb.service;

public class AuthenticationResult extends AbstractEvdbResult {

    protected final static String XML_USER_KEY     = "user_key";

    protected final static String XML_ERROR_KEY    = "error";

    protected final static String XML_NONCE_KEY    = "nonce";

    protected final static String XML_ERROR_STRING = "string";

    protected final static String XML_ERROR_DESC   = "description";

    private String                mNonce;

    private String                mUserKey;

    private String                mErrorString;

    private String                mErrorDesc;

    public String getErrorString() {

        return mErrorString;
    }

    public void setErrorString(final String pErrorStr) {

        mErrorString = pErrorStr;

    }

    public String getErrorDesc() {

        return mErrorDesc;
    }

    public void setErrorDesc(final String pErrorDesc) {

        mErrorDesc = pErrorDesc;

    }

    public String getNonce() {

        return mNonce;
    }

    public void setNonce(final String pNonce) {

        mNonce = pNonce;
    }

    public String getUserKey() {

        return mUserKey;
    }

    public void setUserKey(final String pUserKey) {

        mUserKey = pUserKey;
    }

    @Override
    public Boolean success() {

        return mUserKey != null;
    }

}

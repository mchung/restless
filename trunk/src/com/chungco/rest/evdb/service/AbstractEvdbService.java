
package com.chungco.rest.evdb.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.chungco.rest.AbstractRestService;
import com.chungco.rest.RestUtils;
import com.chungco.rest.evdb.IEvdbConfig;
import com.chungco.rest.evdb.exception.EvdbException;
import com.chungco.rest.exception.MalformedXmlException;
import com.chungco.rest.exception.RestCommandException;

public abstract class AbstractEvdbService<R extends AbstractEvdbResult> extends AbstractRestService<R> {

    protected final static String KEY_APP_KEY  = "app_key";

    protected static final String KEY_USERNAME = "user";

    protected static final String KEY_PASSWORD = "password";

    protected static final String KEY_USER_KEY = "user_key";

    private IEvdbConfig           mConfig;

    protected void setEvdbConfig(final IEvdbConfig pConfig) {

        mConfig = pConfig;

    }

    protected IEvdbConfig getEvdbConfig() {

        return mConfig;

    }

    public void setAppKey(final String pAppKey) {

        setParam(KEY_APP_KEY, pAppKey);
    }

    public void setUsername(final String pUsername) {

        setParam(KEY_USERNAME, pUsername);
    }

    public void setPassword(final String pPassword) {

        setParam(KEY_PASSWORD, pPassword);
    }

    public void setUserKey(final String pUserKey) {

        setParam(KEY_USER_KEY, pUserKey);
    }

    @Override
    protected R doExecute() throws InterruptedException, RestCommandException {

        R r = null;

        try {

            final URL url = new URL(getEndpointURL());
            r = doRequest(url);

        } catch (final MalformedXmlException mxmle) {
            throw new EvdbException(mxmle.getMessage(), mxmle.getCause());
        } catch (final IOException ioe) {
            throw new EvdbException("I/O error: " + ioe.getMessage(), ioe);
        }

        return r;
    }

    protected R doRequest(final URL pUrl) throws IOException, MalformedXmlException, EvdbException {

        R r;
        final URLConnection conn = pUrl.openConnection();

        final String xmlStr = RestUtils.streamToString(conn.getInputStream());

        r = processXml(xmlStr);
        return r;
    }

    /**
     * Implementation is responsible for producing the proper results.
     * 
     * @param pXmlStr
     * @return
     * @throws MalformedXmlException
     *             If the XML couldn't be parsed
     */
    protected R processXml(final String pXmlStr) throws MalformedXmlException, EvdbException {

        preProcessXml(pXmlStr);
        final R x = doParseXml(pXmlStr);
        x.setXmlResponse(pXmlStr);
        postProcessXml(x);
        return x;
    }

    protected abstract R doParseXml(final String pXmlStr) throws MalformedXmlException;

    protected void preProcessXml(final String pXmlStr) throws MalformedXmlException, EvdbException {

        // hook for subclass

    }

    protected void postProcessXml(final R x) throws MalformedXmlException, EvdbException {

        // hook for subclass

    }

    /** Construct args for a GET method */
    protected String makeGET(final String... pKeys) {

        final StringBuilder sb = new StringBuilder();
        for (int i = 0, j = pKeys.length; i < j; i++) {
            final String key = pKeys[i];
            final String val = getParam(key);
            if (i != 0) {
                sb.append("&");
            }
            sb.append(key + "=" + val);
        }
        return sb.toString();
    }

}

package com.chungco.rest.boxnet.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.chungco.rest.AbstractRestService;
import com.chungco.rest.RestUtils;
import com.chungco.rest.boxnet.BoxConstants;
import com.chungco.rest.boxnet.IBoxConfig;
import com.chungco.rest.boxnet.exception.BoxException;
import com.chungco.rest.exception.MalformedXmlException;
import com.chungco.rest.exception.RestCommandException;

/**
 * Template pattern, abstract class (invariant)
 * 
 * @author Marc Chung <mchung@gmail.com>
 * @param <R>
 *            A result type bounded by
 *            {@link com.chungco.rest.boxnet.service.AbstractBoxResult}
 */
public abstract class AbstractBoxService<R extends AbstractBoxResult> extends AbstractRestService<R> {

    protected final static String KEY_SID = "sid";

    private IBoxConfig            mBoxConfig;

    protected void setBoxConfig(final IBoxConfig pBoxConfig) {

        mBoxConfig = pBoxConfig;

    }

    protected IBoxConfig getBoxConfig() {

        return mBoxConfig;

    }

    public void setSID(final String pSID) {

        setParam(KEY_SID, pSID);
    }

    /**
     * The execute life cycle is as follows:
     * <ol>
     * <li>Fetch the service's end point URL
     * <li>Create a URL connection
     * <li>Write the service's XML stream to the URLConnection's output stream
     * <li>Fetch the results back and have the implementing service parse the
     * results
     * <li>Return parsed result type, R
     * </ol>
     * In the future, this method will explore
     * <ol>
     * <li>Timeout settings
     * <li>Possibly using the Jakarta Commons HTTPClient instead of a
     * URLConnection
     * <li>Encrypted connections
     * <li>A transport layer that switches between SOAP, REST, etc
     * <li>Support for Delicious, Flickr and all those other fun APIs coming
     * very soon
     * <li>Support for HTTP authentication.
     * 
     * @return The generic type as implemented by the subclass
     */
    @Override
    protected R doExecute() throws InterruptedException, RestCommandException {

        R r = null;

        try {

            final URL url = new URL(getEndpointURL());
            r = doRequest(url);

        } catch (final MalformedXmlException mxmle) {
            throw new BoxException(mxmle.getMessage(), mxmle.getCause());
        } catch (final IOException ioe) {
            throw new BoxException("I/O error: " + ioe.getMessage(), ioe);
        }

        return r;

    }

    /**
     * Executes a typical HTTP request/response. Override this if you want to
     * perform complex HTTP requests
     * 
     * @param pUrl
     * @return
     * @throws IOException
     * @throws MalformedXmlException
     * @throws BoxException
     */
    protected R doRequest(final URL pUrl) throws IOException, MalformedXmlException, BoxException {

        R r;
        final String xmlRequest = getRequestXml();
        final URLConnection conn = pUrl.openConnection();
        conn.setDoOutput(true);

        RestUtils.writeStringToStream(xmlRequest, conn.getOutputStream());
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
    protected R processXml(final String pXmlStr) throws MalformedXmlException, BoxException {

        preProcessXml(pXmlStr);
        final R x = doParseXml(pXmlStr);
        x.setXmlResponse(pXmlStr);
        postProcessXml(x);
        return x;
    }

    protected abstract R doParseXml(final String pXmlStr) throws MalformedXmlException;

    protected void preProcessXml(final String pXmlStr) throws MalformedXmlException, BoxException {

        // hook for subclass

    }

    protected void postProcessXml(final R x) throws MalformedXmlException, BoxException {

        // hook for subclass
        if (BoxConstants.PARSE_ERROR.equals(x.getStatus())) {
            throw new BoxException("A parse error occurred: " + x.getXmlResponse());
        } else if (BoxConstants.UPGRADING.equals(x.getStatus())) {
            throw new BoxException("An upgrade is occurring: " + x.getXmlResponse());
        }

    }

    /**
     * Returns a String containing a fully populated XML request.
     * 
     * @return
     */
    protected String getRequestXml() {

        // Asks child for the Xml string
        String xml = loadXml();

        // Asks parent to populate Xml string with parameters
        xml = makeXML(xml);

        // Removes @var@
        xml = xml.replaceAll(RestUtils.getEnRE(), "");

        return xml;
    }

    /**
     * Implementation is responsible for loading the XML file and returning it
     * in as a String. Implementors are free to load the XML file in any manner
     * they wish.
     * 
     * @return Returns the contents of an XML request
     */
    protected abstract String loadXml();

}

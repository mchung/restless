package com.chungco.rest.boxnet.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.chungco.core.Stack;
import com.chungco.core.http.MultiPartFormOutputStream;
import com.chungco.core.xml.IXmlSaxAdapter;
import com.chungco.core.xml.IXmlSaxParser;
import com.chungco.core.xml.XmlElement;
import com.chungco.rest.RestUtils;
import com.chungco.rest.boxnet.exception.BoxException;
import com.chungco.rest.exception.MalformedXmlException;

public class FileUploadService extends AbstractBoxService<FileUploadResult> {

    public final static String KEY_FILE_NAME    = "new_file1";

    public final static String KEY_REDIRECT     = "redirect";

    public final static String KEY_LOCATION     = "location";

    public final static String KEY_UPLOAD_FILES = "upload_files";

    private File               mFile;

    @Override
    public String getEndpointURL() {

        final String sid = getParam(KEY_SID);
        return getBoxConfig().getBoxNetHost() + "/ping/upload/" + sid;
    }

    @Override
    protected FileUploadResult doRequest(final URL pUrl) throws IOException, MalformedXmlException, BoxException {

        /**
         * <code>
         <form action="http://www.box.net/ping/upload/fd34c9acc77aefc72576eaeaf3fa4086" 
         enctype="multipart/form-data" method="POST">
         <input type="file" name="new_file1" />
         <input type="hidden" name="redirect" value="http://www.mydomain.net/box
         /success.php?PHPSESSID=fd34c9acc77aefc72576eaeaf3fa4086" />
         <input type="hidden" name="location" value="0" /> 
         <input type="submit" name="upload_files" value="Upload File" />
         </form>
         </code>
         */

        FileUploadResult r;
        final String boundary = MultiPartFormOutputStream.createBoundary();
        final URLConnection urlConn = MultiPartFormOutputStream.createConnection(pUrl);
        urlConn.setRequestProperty("Accept", "*/*");
        urlConn.setRequestProperty("Content-Type", MultiPartFormOutputStream.getContentType(boundary));
        urlConn.setRequestProperty("Connection", "Keep-Alive");
        urlConn.setRequestProperty("Cache-Control", "no-cache");

        final MultiPartFormOutputStream out = new MultiPartFormOutputStream(urlConn.getOutputStream(), boundary);

        final String fileName = getParam(KEY_FILE_NAME);
        out.writeFile(KEY_FILE_NAME, "application/octet-stream", fileName, new FileInputStream(getFile()));

        final String location = getParam(KEY_LOCATION);
        out.writeField(KEY_LOCATION, location);

        final String uploadfiles = getParam(KEY_UPLOAD_FILES);
        out.writeField(KEY_UPLOAD_FILES, uploadfiles);
        out.close();

        final String xmlStr = IOUtils.toString(urlConn.getInputStream());
        r = processXml(xmlStr);
        return r;
    }

    @Override
    protected FileUploadResult doParseXml(final String pXmlStr) throws MalformedXmlException {

        final FileUploadResult result = new FileUploadResult();

        final IXmlSaxParser populate = new IXmlSaxAdapter() {

            public void startContent(final XmlElement kp, final String pXmlText, final Stack<XmlElement> pParents) {

                /***************************************************************
                 * <code>
                 <xml> 
                 <files> 
                 <file> 
                 <status>upload_ok</status>
                 <file_name>notice-blog.html</file_name> 
                 <size>6506</size>
                 <user_id>63833</user_id> 
                 <shared>0</shared> 
                 <folder_id>0</folder_id>
                 <file_id>12205649</file_id>
                 </file> 
                 </files> 
                 </xml>
                 </code>
                 **************************************************************/

                final String xmltag = kp.key();
                if (xmltag.equals(FileUploadResult.XML_FILE_NAME)) {
                    result.setName(pXmlText);
                } else if (xmltag.equals(FileUploadResult.XML_SIZE)) {
                    result.setSize(Long.parseLong(pXmlText));
                } else if (xmltag.equals(FileUploadResult.XML_USER_ID)) {
                    result.setUserId(pXmlText);
                } else if (xmltag.equals(FileUploadResult.XML_SHARED)) {
                    result.setShared(pXmlText);
                } else if (xmltag.equals(FileUploadResult.XML_FOLDER_ID)) {
                    result.setFolderId(pXmlText);
                } else if (xmltag.equals(FileUploadResult.XML_FILE_ID)) {
                    result.setFileId(pXmlText);
                } else if (xmltag.equals(FileUploadResult.XML_STATUS)) {
                    result.setStatus(pXmlText);
                }

            }

        };

        try {
            RestUtils.parseXml2(pXmlStr, populate);
        } catch (MalformedXmlException mxe) {
            throw new MalformedXmlException("Couldn't upload file to Box.net.", mxe.getCause());
        }

        return result;

    }

    @Override
    protected String loadXml() {

        throw new UnsupportedOperationException("Not supported by this service");
    }

    public void setFilename(final String pFilename) {

        setParam(KEY_FILE_NAME, pFilename);
    }

    public void setRedirect(final String pRedirect) {

        setParam(KEY_REDIRECT, pRedirect);
    }

    public void setLocation(final String pLocation) {

        setParam(KEY_LOCATION, pLocation);
    }

    public void setSubmit() {

        setParam(KEY_UPLOAD_FILES, "Upload File");
    }

    public void setFile(final File pFile) {

        mFile = pFile;
        setFilename(pFile.getName());

    }

    public File getFile() {

        return mFile;

    }

}

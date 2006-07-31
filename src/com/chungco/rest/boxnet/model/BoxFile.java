/**
 * 
 */
package com.chungco.rest.boxnet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a File
 * 
 * @see http://dev.box.net/api-documentation
 * @author mchung
 */
public class BoxFile implements IBoxItem {

    private String             mParentFolderId;

    private String             mId;

    private String             mFileName, mKeywords, mShared, mSharedLink, mThumbnail;

    private Long               mSize;

    private Long               mDateCreated;

    private Long               mDateUpdated;

    private final List<Tag>    mTags;

    public final static String XML_FILES          = "files";

    public final static String XML_TAGS           = "tags";

    public final static String XML_FILE           = "file";

    public final static String XML_FILE_ID        = "id";

    public final static String XML_FILE_NAME      = "file_name";

    public final static String XML_FILE_KEYWORD   = "keyword";

    public final static String XML_FILE_SHARED    = "shared";

    public final static String XML_FILE_SIZE      = "size";

    public final static String XML_FILE_CREATED   = "created";

    public final static String XML_FILE_UPDATED   = "updated";

    public final static String XML_FILE_THUMBNAIL = "thumbnail";

    public BoxFile(/* final BoxFolder pParent */) {

        // if (pParent == null) {
        // throw new IllegalArgumentException("File parent may not be null");
        // }
        //
        // mParent = pParent;
        // pParent.addFile(this);

        mId = "";
        mFileName = "";
        mKeywords = "";
        mShared = "";
        mSharedLink = "";
        mThumbnail = "";
        mSize = 0L;
        mDateCreated = 0L;
        mDateUpdated = 0L;

        mTags = new ArrayList<Tag>();

    }

    public Long getDateCreated() {

        return mDateCreated;
    }

    public void setDateCreated(final Long dateCreated) {

        if (dateCreated == null)
            mDateCreated = 0L;
        else
            mDateCreated = dateCreated;
    }

    public Long getDateUpdated() {

        return mDateUpdated;
    }

    public void setDateUpdated(final Long dateUpdated) {

        if (dateUpdated == null)
            mDateUpdated = 0L;
        else
            mDateUpdated = dateUpdated;
    }

    public String getName() {

        return mFileName;
    }

    public void setName(final String fileName) {

        mFileName = fileName;
    }

    public String getId() {

        return mId;
    }

    public void setId(final String pId) {

        mId = pId;
    }

    public String getKeywords() {

        return mKeywords;
    }

    public void setKeywords(final String pKeywords) {

        mKeywords = pKeywords;
    }

    public String getShared() {

        return mShared;
    }

    public void setShared(final String shared) {

        mShared = shared;
    }

    public String getSharedLink() {

        return mSharedLink;
    }

    public void setSharedLink(final String sharedLink) {

        mSharedLink = sharedLink;
    }

    public Long getSize() {

        return mSize;
    }

    public void setSize(final Long size) {

        if (size == null)
            mSize = 0L;
        else
            mSize = size;
    }

    public List<Tag> getTags() {

        return mTags;
    }

    // public void setTags(final List<Tag> tags) {
    //
    // mTags = tags;
    // }

    public String getThumbnail() {

        return mThumbnail;
    }

    public void setThumbnail(final String thumbnail) {

        mThumbnail = thumbnail;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        // sb.append("parent=" + mParent.getName());
        sb.append("file=" + getName() + " (id=" + getId() + ")");
        return sb.toString();
    }

    public String toXml() {

        StringBuilder sb = new StringBuilder();
        sb.append("<" + BoxFile.XML_FILE);
        sb.append(" " + BoxFile.XML_FILE_ID + "=\"" + getId() + "\"");
        sb.append(" " + BoxFile.XML_FILE_NAME + "=\"" + getName() + "\"");
        sb.append(" " + BoxFile.XML_FILE_KEYWORD + "=\"" + getKeywords() + "\"");
        sb.append(" " + BoxFile.XML_FILE_SHARED + "=\"" + getShared() + "\"");
        sb.append(" " + BoxFile.XML_FILE_SIZE + "=\"" + getSize() + "\"");
        sb.append(" " + BoxFile.XML_FILE_CREATED + "=\"" + getDateCreated() + "\"");
        sb.append(" " + BoxFile.XML_FILE_UPDATED + "=\"" + getDateUpdated() + "\"");
        sb.append(" " + BoxFile.XML_FILE_THUMBNAIL + "=\"" + mThumbnail + "\"");

        // TODO no support for tags just yet
        if (mTags.size() == 0 || true) {
            sb.append("/>");
        } else {
            sb.append("</" + BoxFile.XML_FILE + ">");
        }

        return sb.toString();
    }

    public String getFolderId() {

        return mParentFolderId;
    }

    public void setFolderId(final String pParentId) {

        mParentFolderId = pParentId;
    }

}
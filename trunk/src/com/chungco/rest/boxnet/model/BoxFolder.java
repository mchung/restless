/**
 * 
 */
package com.chungco.rest.boxnet.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a Folder
 * 
 * @see http://dev.box.net/api-documentation
 * @author mchung
 */
public class BoxFolder implements IBoxItem {

    private final BoxFolder       mParent;

    private String                mId, mName, mShared, mSharedLink;

    private final List<BoxFile>   mFiles;

    private final List<BoxFolder> mFolders;

    private final List<Tag>       mTags;

    public final static String    XML_FOLDERS       = "folders";

    public final static String    XML_FOLDER        = "folder";

    public final static String    XML_FOLDER_ID     = "id";

    public final static String    XML_FOLDER_NAME   = "name";

    public final static String    XML_FOLDER_SHARED = "shared";

    public BoxFolder(final BoxFolder pParent) {

        mParent = pParent;
        if (mParent != null) {
            mParent.addFolder(this);
        }

        mId = "";
        mName = "";
        mShared = "";
        mSharedLink = "";

        mFiles = new ArrayList<BoxFile>();
        mFolders = new ArrayList<BoxFolder>();
        mTags = new ArrayList<Tag>();

    }

    public void addFile(final BoxFile pFile) {

        mFiles.add(pFile);
    }

    public void addFolder(final BoxFolder pFolder) {

        mFolders.add(pFolder);
    }

    public void addTag(final Tag pTag) {

        mTags.add(pTag);
    }

    public List<BoxFile> getFiles() {

        return mFiles;
    }

    // public void setFiles(final List<File> files) {
    //
    // mFiles = files;
    // }

    public List<BoxFolder> getFolders() {

        return mFolders;
    }

    //
    // public void setFolders(final List<Folder> folders) {
    //
    // mFolders = folders;
    // }

    public String getId() {

        return mId;
    }

    public void setId(final String id) {

        mId = id;
    }

    public String getName() {

        return mName;
    }

    public void setName(final String name) {

        mName = name;
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

    public List<Tag> getTags() {

        return mTags;
    }

    // public void setTags(final List<Tag> tags) {
    //
    // mTags = tags;
    // }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        sb.append("folder=" + getName() + " (id=" + getId() + ")");
        return sb.toString();
    }

    public BoxFolder getParent() {

        return mParent;
    }

    public String toXml() {

        final StringBuilder sb = new StringBuilder();
        writeFolder(this, sb);
        return sb.toString();

    }

    private void writeFolder(final BoxFolder pFolder, final StringBuilder sb) {

        sb.append("<" + BoxFolder.XML_FOLDER);
        sb.append(" " + BoxFolder.XML_FOLDER_ID + "=\"" + pFolder.getId() + "\"");
        sb.append(" " + BoxFolder.XML_FOLDER_NAME + "=\"" + pFolder.getName() + "\"");
        sb.append(" " + BoxFolder.XML_FOLDER_SHARED + "=\"" + pFolder.getShared() + "\"");
        sb.append(">");

        final List<BoxFolder> folderList = pFolder.getFolders();
        sb.append("<" + BoxFolder.XML_FOLDERS + ">");
        for (final BoxFolder f : folderList) {
            writeFolder(f, sb);
        }
        sb.append("</" + BoxFolder.XML_FOLDERS + ">");

        final List<BoxFile> fileList = pFolder.getFiles();
        sb.append("<" + BoxFile.XML_FILES + ">");
        for (final BoxFile f : fileList) {
            sb.append(f.toXml());
        }
        sb.append("</" + BoxFile.XML_FILES + ">");

        sb.append("</" + BoxFolder.XML_FOLDER + ">");

    }

}
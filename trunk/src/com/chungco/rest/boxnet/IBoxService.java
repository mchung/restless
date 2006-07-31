
package com.chungco.rest.boxnet;

import java.io.File;

import com.chungco.rest.boxnet.model.SharingOpts;
import com.chungco.rest.boxnet.model.Target;
import com.chungco.rest.boxnet.service.AuthorizationResult;
import com.chungco.rest.boxnet.service.DeleteItemResult;
import com.chungco.rest.boxnet.service.FileDownloadResult;
import com.chungco.rest.boxnet.service.FileListingResult;
import com.chungco.rest.boxnet.service.FileUploadResult;
import com.chungco.rest.boxnet.service.FolderCreationResult;
import com.chungco.rest.boxnet.service.MoveItemResult;
import com.chungco.rest.boxnet.service.RenameItemResult;
import com.chungco.rest.exception.RestCommandException;

public interface IBoxService {

    /**
     * <code>
     * IBoxService box = new BoxService();
     * AuthorizationResult result = box.login("example@example.com", "example");
     * </code>
     * 
     * @param pUsername
     *            Your login name
     * @param pPassword
     *            Your password
     * @return The results of a login, successful or unsuccessful.
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public AuthorizationResult login(final String pUsername, final String pPassword) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * IBoxService box = new BoxService();
     * DeleteItemResult result = box.delete(Target.FOLDER, "03948");
     * </code>
     * 
     * @param pType
     *            Folder or File type
     * @param pItemId
     *            Item Id
     * @return The results of a delete, successful or unsuccessful.
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public DeleteItemResult delete(final Target pType, final String pItemId) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * IBoxService box = new BoxService();
     * FileListingResult result = box.list("0", "0");
     * </code>

     */
    public FileListingResult list(final String pFolderId, final String pLevel) throws InterruptedException, RestCommandException;

    public FolderCreationResult makedir(final String pParentId, final String pFolderName, final SharingOpts pSharingOpts) throws InterruptedException, RestCommandException;

    public FileDownloadResult download(final String pFileId, final String pFileName, final String pFolderPath) throws InterruptedException, RestCommandException;

    public MoveItemResult move(final Target pType, final String pItemId, final String pFolderId) throws InterruptedException, RestCommandException;

    public RenameItemResult rename(final Target pType, final String pItemId, final String pNewName) throws InterruptedException, RestCommandException;

    public FileUploadResult upload(final File pFile, final String pFilename, final String pLocationId) throws InterruptedException, RestCommandException;

}
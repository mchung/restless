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
     *            The Id of the item, file or folder
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
     * 
     * @param pFolderId
     *            Folder Id
     * @param pLevel
     *            How many levels deep
     * @return The results of a file listing
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public FileListingResult list(final String pFolderId, final String pLevel) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * IBoxService box = new BoxService();
     * SharingOpts opts = new SharingOpts(Share.IGNORE);
     * FolderCreationResult create = service.makedir("0", "nameOfFileOnBoxnet, opts);
     * </code>
     * 
     * @param pParentId
     *            The Id of the parent folder
     * @param pFolderName
     *            The name of the folder
     * @param pSharingOpts
     *            Sharing options
     * @return The results of the folder creation
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public FolderCreationResult makedir(final String pParentId, final String pFolderName, final SharingOpts pSharingOpts) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * BoxFile file = ... 
     * IBoxService box = new BoxService();
     * FileDownloadResult download = service.download(file.getId(), "nameOfFileOnDisk", "/");
     * </code>
     * 
     * @param pFileId
     *            The Id of the file
     * @param pFileName
     *            The name of the file on disk
     * @param pFolderPath
     *            The directory to download the file to
     * @return The results of the file download
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public FileDownloadResult download(final String pFileId, final String pFileName, final String pFolderPath) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * BoxFile file = ... 
     * IBoxService box = new BoxService();
     * MoveItemResult move = service.move(Target.FILE, file.getId(), "0");
     * </code>
     * 
     * @param pType
     *            The type of item, file or folder
     * @param pItemId
     *            The item's Id
     * @param pFolderId
     *            The parent folder's Id
     * @return The results of the move operation
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public MoveItemResult move(final Target pType, final String pItemId, final String pFolderId) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * BoxFile file = ... 
     * IBoxService box = new BoxService();
     * MoveItemResult move = service.move(Target.FILE, file.getId(), "newFileNameOnBoxnet");
     * </code>
     * 
     * @param pType
     *            The type of item, file or folder
     * @param pItemId
     *            The item's Id
     * @param pNewName
     *            The name of the new file
     * @return The results of the rename operation
     * @throws InterruptedException
     *             If the operation is interrupted
     * @throws RestCommandException
     *             If the operation fails to complete
     */
    public RenameItemResult rename(final Target pType, final String pItemId, final String pNewName) throws InterruptedException, RestCommandException;

    /**
     * <code>
     * IBoxService box = new BoxService();
     * FileUploadResult upload = box.upload(new File("/path/to/file.xxx"), "newFile.xxx", "0");
     * </code>
     * 
     * @param pFile
     *            A reference to a File object
     * @param pFilename
     *            The name of the file. If null, uses the name of the file on
     *            the local disk
     * @param pLocationId
     *            The Id of the parent folder
     * @return The results of a file upload
     * @throws InterruptedException
     * @throws RestCommandException
     */
    public FileUploadResult upload(final File pFile, final String pFilename, final String pLocationId) throws InterruptedException, RestCommandException;

}
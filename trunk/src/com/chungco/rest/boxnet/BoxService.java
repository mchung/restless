
package com.chungco.rest.boxnet;

import java.io.File;

import com.chungco.rest.boxnet.model.SharingOpts;
import com.chungco.rest.boxnet.model.Target;
import com.chungco.rest.boxnet.service.AuthorizationResult;
import com.chungco.rest.boxnet.service.AuthorizationService;
import com.chungco.rest.boxnet.service.BoxServiceFactory;
import com.chungco.rest.boxnet.service.DeleteItemResult;
import com.chungco.rest.boxnet.service.DeleteItemService;
import com.chungco.rest.boxnet.service.FileDownloadResult;
import com.chungco.rest.boxnet.service.FileDownloadService;
import com.chungco.rest.boxnet.service.FileListingResult;
import com.chungco.rest.boxnet.service.FileListingService;
import com.chungco.rest.boxnet.service.FileUploadResult;
import com.chungco.rest.boxnet.service.FileUploadService;
import com.chungco.rest.boxnet.service.FolderCreationResult;
import com.chungco.rest.boxnet.service.FolderCreationService;
import com.chungco.rest.boxnet.service.MoveItemResult;
import com.chungco.rest.boxnet.service.MoveItemService;
import com.chungco.rest.boxnet.service.RenameItemResult;
import com.chungco.rest.boxnet.service.RenameItemService;
import com.chungco.rest.exception.RestCommandException;

/**
 * @author Marc Chung <mchung@gmail.com>
 */
public class BoxService implements IBoxService {

    private BoxServiceFactory mBoxFactory;

    public BoxService() {

        mBoxFactory = new BoxServiceFactory(new PropertiesBoxNetConfig());
    }

    public BoxService(IBoxConfig pConfig) {

        mBoxFactory = new BoxServiceFactory(pConfig);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#login(java.lang.String,
     *      java.lang.String)
     */
    public AuthorizationResult login(final String pUsername, final String pPassword) throws InterruptedException, RestCommandException {

        final AuthorizationService service = mBoxFactory.getAuthorizationService();
        service.setLogin(pUsername, pPassword);

        final AuthorizationResult result = service.execute();

        final BoxLoginProvider provider = new BoxLoginProvider(pUsername, pPassword, result.getSID());
        mBoxFactory.setCredentials(provider);

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#delete(com.chungco.rest.boxnet.model.Target,
     *      java.lang.String)
     */
    public DeleteItemResult delete(final Target pType, final String pItemId) throws InterruptedException, RestCommandException {

        final DeleteItemResult result;
        final DeleteItemService service = mBoxFactory.getDeleteItemService();
        service.setTargetType(pType);
        service.setItem(pItemId);
        result = service.execute();
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#list(java.lang.String,
     *      java.lang.String)
     */
    public FileListingResult list(final String pFolderId, final String pLevel) throws InterruptedException, RestCommandException {

        final FileListingResult result;
        final FileListingService service = mBoxFactory.getFileListingService();
        service.setFolderId(pFolderId);
        service.setLevel(pLevel);
        result = service.execute();

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#makedir(java.lang.String,
     *      java.lang.String, com.chungco.rest.boxnet.model.SharingOpts)
     */
    public FolderCreationResult makedir(final String pParentId, final String pFolderName, final SharingOpts pSharingOpts) throws InterruptedException, RestCommandException {

        final FolderCreationResult result;
        final FolderCreationService createFolder = mBoxFactory.getFolderCreationService();
        createFolder.setParentFolderId(pParentId);
        createFolder.setFolderName(pFolderName);
        createFolder.setSharingOptions(pSharingOpts);
        result = createFolder.execute();

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#download(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    public FileDownloadResult download(final String pFileId, final String pFileName, final String pFolderPath) throws InterruptedException, RestCommandException {

        final FileDownloadResult result;
        final FileDownloadService download = mBoxFactory.getFileDownloadService();
        download.setFileId(pFileId);
        download.setFileName(pFileName);
        download.setSaveFolder(pFolderPath);
        result = download.execute();
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#move(com.chungco.rest.boxnet.model.Target,
     *      java.lang.String, java.lang.String)
     */
    public MoveItemResult move(final Target pType, final String pItemId, final String pFolderId) throws InterruptedException, RestCommandException {

        final MoveItemResult result;
        final MoveItemService moveItem = mBoxFactory.getMoveItemSerice();
        moveItem.setItem(pItemId);
        moveItem.setTargetType(pType);
        moveItem.setDestinationFolder(pFolderId);
        result = moveItem.execute();

        return result;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#rename(com.chungco.rest.boxnet.model.Target,
     *      java.lang.String, java.lang.String)
     */
    public RenameItemResult rename(final Target pType, final String pItemId, final String pNewName) throws InterruptedException, RestCommandException {

        final RenameItemResult result;

        final RenameItemService service = mBoxFactory.getRenameItemSerice();
        service.setTargetType(pType);
        service.setItem(pItemId);
        service.setNewName(pNewName);
        result = service.execute();

        return result;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.chungco.rest.boxnet.IBoxService#upload(java.io.File,
     *      java.lang.String, java.lang.String)
     */
    public FileUploadResult upload(final File pFile, final String pFilename, final String pLocationId) throws InterruptedException, RestCommandException {

        final FileUploadResult result;

        final FileUploadService service = mBoxFactory.getFileCreationService();
        service.setFile(pFile);
        if (pFilename != null) {
            service.setFilename(pFilename);
        }
        service.setLocation(pLocationId);
        result = service.execute();

        return result;

    }
}

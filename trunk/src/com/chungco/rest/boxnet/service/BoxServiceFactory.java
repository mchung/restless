
package com.chungco.rest.boxnet.service;

import com.chungco.rest.AbstractServiceFactory;
import com.chungco.rest.boxnet.BoxLoginProvider;
import com.chungco.rest.boxnet.IBoxConfig;

/**
 * Factory class responsible for creating and managing
 * {@link com.chungco.rest.boxnet.service.AbstractBoxService} services.
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public class BoxServiceFactory extends AbstractServiceFactory<BoxLoginProvider> {

    private final IBoxConfig mBoxConfig;

    public BoxServiceFactory(final IBoxConfig pBoxConfig) {

        mBoxConfig = pBoxConfig;

    }

    public AuthorizationService getAuthorizationService() {

        final AuthorizationService service = new AuthorizationService();
        service.setBoxConfig(mBoxConfig);
        return service;
    }

    public DeleteItemService getDeleteItemService() {

        final DeleteItemService service = new DeleteItemService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

    public FileDownloadService getFileDownloadService() {

        final FileDownloadService service = new FileDownloadService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

    public FileListingService getFileListingService() {

        final FileListingService service = new FileListingService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

    public FileUploadService getFileCreationService() {

        final FileUploadService service = new FileUploadService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

    public FolderCreationService getFolderCreationService() {

        final FolderCreationService service = new FolderCreationService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

    public MoveItemService getMoveItemSerice() {

        final MoveItemService service = new MoveItemService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

    public RenameItemService getRenameItemSerice() {

        final RenameItemService service = new RenameItemService();
        service.setBoxConfig(mBoxConfig);
        service.setSID(mLoginCredentials.getSID());
        return service;
    }

}

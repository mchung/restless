package com.chungco.rest.boxnet.service;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.chungco.rest.boxnet.BoxService;
import com.chungco.rest.boxnet.IBoxService;
import com.chungco.rest.boxnet.model.Share;
import com.chungco.rest.boxnet.model.SharingOpts;
import com.chungco.rest.boxnet.model.Target;
import com.chungco.rest.exception.RestCommandException;

/**
 * Welcome! This is a test case
 * 
 * @author mchung
 */
public class BoxServiceTestCase extends TestCase {

    public void testLogin() {

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());
            System.out.println(login);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testList() {

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());

            FileListingResult list = service.list("0", "0");
            Assert.assertEquals(list.getFolder().getName(), "MyBox");

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testCreateFolderAndDelete() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());

            // Create
            final SharingOpts opts = new SharingOpts(Share.IGNORE);
            final FolderCreationResult create = service.makedir("0", "BoxServiceTestCaseTest-" + noise, opts);
            Assert.assertTrue(create.success());

            // Clean up
            final DeleteItemResult delete = service.delete(Target.FOLDER, create.getFolderId());
            Assert.assertTrue(delete.success());

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testUploadAndDelete() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());

            final String name = "BoxServiceTestCase-" + noise + ".xxx";

            // Upload
            final URL url = ClassLoader.getSystemResource(BoxTestCase.testUploadFile);
            final FileUploadResult upload = service.upload(new File(url.getFile()), name, "0");
            Assert.assertTrue(upload.success());

            // Clean up
            final DeleteItemResult delete = service.delete(Target.FILE, upload.getFile().getId());
            Assert.assertTrue(delete.success());

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    /**
     * Warning: This test case creates a temporary file on the main disk.
     */
    public void testUploadAndDownload() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());

            final String name = "BoxServiceTestCase-" + noise + ".xxx";

            // Upload file
            final URL url = ClassLoader.getSystemResource(BoxTestCase.testUploadFile);
            final FileUploadResult upload = service.upload(new File(url.getFile()), name, "0");
            Assert.assertTrue(upload.success());

            // Download file to root directory, i.e. C:\ or / under non-Windows
            // machines.
            // TODO Download to a TEMP folder in $HOME
            final FileDownloadResult download = service.download(upload.getFile().getId(), name, "/");
            Assert.assertTrue(download.success());

            // Clean up
            final DeleteItemResult delete = service.delete(Target.FILE, upload.getFile().getId());
            Assert.assertTrue(delete.success());

            final File file = new File("/", name);
            file.delete();

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testMoveFile() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());

            final String filename = "BoxServiceTestCase-" + noise + ".xxx";

            final String foldername = filename + "_folder";

            // Upload a tempr. file.
            final URL url = ClassLoader.getSystemResource(BoxTestCase.testUploadFile);
            final FileUploadResult upload = service.upload(new File(url.getFile()), filename, "0");
            Assert.assertTrue(upload.success());

            // Create a tempr folder
            final SharingOpts opts = new SharingOpts(Share.IGNORE);
            final FolderCreationResult create = service.makedir("0", foldername, opts);
            Assert.assertTrue(create.success());

            // Move the file
            final MoveItemResult move = service.move(Target.FILE, upload.getFile().getId(), create.getFolderId());
            Assert.assertTrue(move.success());

            // Assert that there exist a file in the newly created folder
            final FileListingResult list = service.list(create.getFolderId(), "0");
            Assert.assertTrue(list.success());
            Assert.assertEquals(list.getFolder().getFiles().get(0).getId(), upload.getFile().getId());

            // Clean up
            final DeleteItemResult delete = service.delete(Target.FOLDER, create.getFolderId());
            Assert.assertTrue(delete.success());

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testRenameFolder() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        try {
            final MockBoxnetLogin provider = new MockBoxnetLogin();

            final IBoxService service = new BoxService();
            final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
            Assert.assertTrue(login.success());

            final String foldername = "BoxServiceTestCase-" + noise + ".xxx";
            final String newfoldername = foldername + "_new_name";

            // Create a tempr folder
            final SharingOpts opts = new SharingOpts(Share.IGNORE);
            final FolderCreationResult create = service.makedir("0", foldername, opts);
            Assert.assertTrue(create.success());

            // Rename the file
            final RenameItemResult move = service.rename(Target.FOLDER, create.getFolderId(), newfoldername);
            Assert.assertTrue(move.success());

            // Clean up
            final DeleteItemResult delete = service.delete(Target.FOLDER, create.getFolderId());
            Assert.assertTrue(delete.success());

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

}

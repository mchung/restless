package com.chungco.rest.boxnet.service;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.chungco.rest.IRestCommand;
import com.chungco.rest.IRestResult;
import com.chungco.rest.RemoteConnection;
import com.chungco.rest.boxnet.model.Share;
import com.chungco.rest.boxnet.model.SharingOpts;
import com.chungco.rest.boxnet.model.Target;
import com.chungco.rest.exception.RestCommandException;

/**
 * A test case. This test case, really isn't a test case. Please examine
 * {@link BoxServiceTestCase} for a real test case
 * 
 * @author mchung
 */
public class BoxTestCase extends TestCase {

    private MockBoxnetLogin     provider       = new MockBoxnetLogin();

    private final static String WD             = "sandbox";

    public final static String  testUploadFile = "UploadTestFile.html.test";

    // TODO Make this test case check file listings after each operation
    // is executed. Use FileListingService. Perform all "work" in a sandbox'd
    // directory

    protected static String     SID;

    protected static String     FILE_ID;

    protected static boolean    previousStatus = true;

    public BoxTestCase(final String pMethodName) {

        super(pMethodName);
    }

    public static Test suite() {

        final TestSuite suite = new TestSuite("BoxNetTestCase Test");

        suite.addTest(new BoxTestCase("testAuthorization"));
        suite.addTest(new BoxTestCase("testFileUpload"));
        suite.addTest(new BoxTestCase("testFileDownload"));
        suite.addTest(new BoxTestCase("testFolderCreation"));
        suite.addTest(new BoxTestCase("testFileListing"));

        return suite;

    }

    protected void preAssert() {

        assertTrue("previous operation failed", previousStatus);
    }

    protected void postAssert(final String m, final AbstractBoxResult r) {

        previousStatus = r.success();
        assertTrue(m + " - " + r.getXmlResponse(), previousStatus);
    }

    public void testAuthorization() {

        preAssert();

        try {

            final AuthorizationService command = new AuthorizationService();
            command.setBoxConfig(new MockBoxConfig());

            final String u = provider.getUsername();
            final String p = provider.getPassword();
            command.setLogin(u, p);
            final AuthorizationResult result = delegate(command);

            // Set the current value, so other services may use it
            SID = result.getSID();

            postAssert("unsuccessful login", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }
    }

    /**
     * API issues
     * <ol>
     * <li>The upload service won't fail if you're trying to upload a file of
     * the same name</li>
     * </ol>
     */
    public void testFileUpload() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        preAssert();
        try {

            final FileUploadService command = new FileUploadService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);

            final URL url = ClassLoader.getSystemResource(testUploadFile);
            command.setFile(new File(url.getFile()));

            final String newFile = testUploadFile + "-" + noise + ".html";

            command.setFilename(newFile);
            command.setLocation("0");
            command.setSubmit();
            final FileUploadResult result = delegate(command);

            // Set the ID of the file I uploaded, so other services may use it
            FILE_ID = result.getFile().getId();

            postAssert("unsuccessful file upload", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    /**
     * API issues
     * <ol>
     * <li>A bad download cannot be deterministically detected. The return
     * result is a HTML page containing human readable info.</li>
     * </ol>
     */
    public void testFileDownload() {

        final SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss-S");
        final String noise = sdf.format(new Date());

        preAssert();
        try {

            final FileDownloadService command = new FileDownloadService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);
            command.setFileId(FILE_ID);

            final String newFile = testUploadFile + "-" + noise + ".html";

            command.setFileName(newFile);
            command.setSaveFolder("/");
            final FileDownloadResult result = delegate(command);

            postAssert("unsuccessful file download", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }
    }

    public void testFolderCreation() {

        preAssert();

        try {
            final FolderCreationService command = new FolderCreationService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);
            command.setFolderName(WD);
            command.setParentFolderId("0");
            final SharingOpts opts = new SharingOpts();
            opts.setSendEmailTo(provider.getUsername());
            opts.setEmailMessage("What is this for?");
            opts.setShareType(Share.SHARE);
            command.setSharingOptions(opts);
            final FolderCreationResult result = delegate(command);

            postAssert("unsuccessful folder creation", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testFileListing() {

        try {
            final FileListingService command = new FileListingService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);
            command.setFolderId("0");

            final FileListingResult result = delegate(command);

            postAssert("unable to list files", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }
    }

    // TODO Make move/copy/rename less reliant on hard coded values

    public void xxx_testMoveItem() {

        try {
            final MoveItemService command = new MoveItemService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);

            String itemId = "0";
            String dest = "0";

            command.setItem(itemId);
            command.setDestinationFolder(dest);

            final MoveItemResult result = delegate(command);

            postAssert("unable to move file", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void xxx_testRenameItem() {

        try {
            final RenameItemService command = new RenameItemService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);

            String itemId = "0";

            command.setItem(itemId);
            command.setNewName("somethingelse.xxx");
            command.setTargetType(Target.FOLDER);

            final RenameItemResult result = delegate(command);

            postAssert("unable to rename file", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void xxx_testDeleteItem() {

        try {
            final DeleteItemService command = new DeleteItemService();
            command.setBoxConfig(new MockBoxConfig());
            command.setSID(SID);

            String itemId = "0";

            command.setItem(itemId);
            command.setTargetType(Target.FOLDER);

            final DeleteItemResult result = delegate(command);

            postAssert("unable to rename file", result);

            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public final static int STYLE = 3;

    private <R extends IRestResult> R delegate(final IRestCommand<R> pCommand) throws InterruptedException, RestCommandException {

        final RemoteConnection con = new RemoteConnection();
        if (STYLE == 1)
            return con.call(pCommand);
        else if (STYLE == 2)
            return con.execute(pCommand);
        else
            return con.submit(pCommand);

    }

}

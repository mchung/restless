
package com.chungco.rest.boxnet.service;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import com.chungco.rest.boxnet.BoxService;
import com.chungco.rest.exception.RestCommandException;

/**
 * This Unit test cancels/interrupts a long-running Box.net task, like File
 * upload. It is still being tested :)
 * 
 * @author mchung
 */

public class CancellableTask extends TestCase {

    private final static MockLoginPassword provider = new MockLoginPassword();

    public void testCancellableThread() {

        Future<?> runningTask = null;

        final ExecutorService backgroundExec = Executors.newCachedThreadPool();
        runningTask = backgroundExec.submit(new Runnable() {

            public void run() {

                try {
                    System.out.println("I was called");
                    final BoxService service = new BoxService(new MockBoxConfig());
                    final AuthorizationResult login = service.login(provider.getUsername(), provider.getPassword());
                    System.out.println(login);

                    final FileListingResult listings = service.list("0", "0");
                    System.out.println(listings);

                    // Just some arbitrary big file
                    final File file = new File("D:\\downloads\\ethereal-setup-0.99.0.exe");
                    final FileUploadResult upload = service.upload(file, null, "0");
                    System.out.println(upload);

                } catch (final InterruptedException ioe) {
                    Thread.currentThread().interrupt();
                    ioe.printStackTrace();
                } catch (final RestCommandException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            backgroundExec.awaitTermination(10, TimeUnit.SECONDS);
            runningTask.cancel(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cancelled");

    }

}

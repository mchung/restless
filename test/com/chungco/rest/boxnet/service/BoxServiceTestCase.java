
package com.chungco.rest.boxnet.service;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.chungco.rest.boxnet.BoxService;
import com.chungco.rest.boxnet.IBoxService;
import com.chungco.rest.exception.RestCommandException;

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

}

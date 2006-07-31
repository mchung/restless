
package com.chungco.rest.evdb.service;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.chungco.rest.evdb.EvdbService;
import com.chungco.rest.evdb.service.venues.VenuesSearchResult;
import com.chungco.rest.exception.RestCommandException;

public class EvdbServiceTestCase extends TestCase {

    public void testLogin() {

        try {
            final MockEvdbLogin provider = new MockEvdbLogin();

            final EvdbService service = new EvdbService();
            final AuthenticationResult result = service.login(provider.getAppKey(), provider.getUsername(), provider.getPassword());
            Assert.assertTrue(result.success());
            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testSecureLogin() {

        try {
            final MockEvdbLogin provider = new MockEvdbLogin();

            final EvdbService service = new EvdbService();
            final AuthenticationResult result = service.slogin(provider.getAppKey(), provider.getUsername(), provider.getPassword());
            Assert.assertTrue(result.success());
            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testVenuesSearch() {

        try {
            final MockEvdbLogin provider = new MockEvdbLogin();

            final EvdbService service = new EvdbService();
            final AuthenticationResult result = service.slogin(provider.getAppKey(), provider.getUsername(), provider.getPassword());
            Assert.assertTrue(result.success());

            final VenuesSearchResult search = service.venuesSearch("sushi", "san diego", false, 10, 1);
            Assert.assertTrue(search.success());
            System.out.println(search);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }
}

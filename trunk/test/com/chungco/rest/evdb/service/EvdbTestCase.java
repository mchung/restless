
package com.chungco.rest.evdb.service;

import junit.framework.TestCase;

import com.chungco.rest.evdb.service.venues.VenuesSearchResult;
import com.chungco.rest.evdb.service.venues.VenuesSearchService;
import com.chungco.rest.exception.RestCommandException;

public class EvdbTestCase extends TestCase {

    public void testBasicAuthenticationService() {

        try {
            final MockEvdbLogin provider = new MockEvdbLogin();
            final MockEvdbConfig config = new MockEvdbConfig();

            final BasicAuthenticationService command = new BasicAuthenticationService();
            command.setEvdbConfig(config);
            command.setUsername(provider.getUsername());
            command.setPassword(provider.getPassword());
            command.setAppKey(provider.getAppKey());

            final AuthenticationResult result = command.execute();
            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testDigestAuthenticationService() {

        try {
            final MockEvdbLogin provider = new MockEvdbLogin();
            final MockEvdbConfig config = new MockEvdbConfig();

            final DigestAuthenticationService command = new DigestAuthenticationService();
            command.setEvdbConfig(config);
            command.setUsername(provider.getUsername());
            command.setPassword(provider.getPassword());
            command.setAppKey(provider.getAppKey());

            final AuthenticationResult result = command.execute();
            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

    public void testVenuesSearchService() {

        try {
            final MockEvdbLogin provider = new MockEvdbLogin();
            final MockEvdbConfig config = new MockEvdbConfig();

            final VenuesSearchService command = new VenuesSearchService();
            command.setEvdbConfig(config);
            command.setUsername(provider.getUsername());
            command.setPassword(provider.getPassword());
            command.setAppKey(provider.getAppKey());
            command.setKeywords("Kazimierz");
            command.setLocation("phoenix, az");
            command.setCountOnly(false);
            command.setPageSize(10);
            command.setPageNumber(1);

            final VenuesSearchResult result = command.execute();
            System.out.println(result);

        } catch (final RestCommandException e) {
            fail("Failed: " + e.getMessage());
        } catch (final InterruptedException e) {
            fail("Failed: " + e.getMessage());
        }

    }

}

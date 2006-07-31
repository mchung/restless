
package com.chungco.rest.evdb;

import com.chungco.rest.evdb.service.AuthenticationResult;
import com.chungco.rest.evdb.service.BasicAuthenticationService;
import com.chungco.rest.evdb.service.DigestAuthenticationService;
import com.chungco.rest.evdb.service.EvdbServiceFactory;
import com.chungco.rest.evdb.service.venues.VenuesSearchResult;
import com.chungco.rest.evdb.service.venues.VenuesSearchService;
import com.chungco.rest.exception.RestCommandException;

public class EvdbService {

    private EvdbServiceFactory mEvdbFactory;

    public EvdbService() {

        mEvdbFactory = new EvdbServiceFactory(new PropertiesEvdbConfig());

    }

    public EvdbService(final IEvdbConfig pConfig) {

        mEvdbFactory = new EvdbServiceFactory(pConfig);
    }

    public AuthenticationResult login(final String pAppKey, final String pUsername, final String pPassword) throws InterruptedException, RestCommandException {

        final BasicAuthenticationService service = mEvdbFactory.getBasicAuthenticationService();
        service.setAppKey(pAppKey);
        service.setUsername(pUsername);
        service.setPassword(pPassword);
        final AuthenticationResult result = service.execute();
        final EvdbLoginProvider loginProvider = new EvdbLoginProvider(pAppKey, pUsername, pPassword, result.getUserKey());
        mEvdbFactory.setCredentials(loginProvider);

        return result;
    }

    public AuthenticationResult slogin(final String pAppKey, final String pUsername, final String pPassword) throws InterruptedException, RestCommandException {

        final DigestAuthenticationService service = mEvdbFactory.getDigestAuthenticationService();
        service.setAppKey(pAppKey);
        service.setUsername(pUsername);
        service.setPassword(pPassword);
        final AuthenticationResult result = service.execute();
        final EvdbLoginProvider loginProvider = new EvdbLoginProvider(pAppKey, pUsername, pPassword, result.getUserKey());
        mEvdbFactory.setCredentials(loginProvider);

        return result;
    }

    public VenuesSearchResult venuesSearch(final String pKeywords, final String pLocation, final boolean pCountOnly, final int pPgSize, final int pPgNo) throws InterruptedException,
            RestCommandException {

        final VenuesSearchService service = mEvdbFactory.getVenuesSearchService();
        service.setKeywords(pKeywords);
        service.setLocation(pLocation);
        service.setCountOnly(pCountOnly);
        service.setPageSize(pPgSize);
        service.setPageNumber(pPgNo);
        VenuesSearchResult result = service.execute();

        return result;

    }

}

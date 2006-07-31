
package com.chungco.rest.evdb.service;

import com.chungco.rest.AbstractServiceFactory;
import com.chungco.rest.evdb.EvdbLoginProvider;
import com.chungco.rest.evdb.IEvdbConfig;
import com.chungco.rest.evdb.service.venues.VenuesSearchService;

/**
 * Factory class responsible for creating and managing
 * {@link com.chungco.rest.boxnet.service.AbstractBoxNetService} services.
 * 
 * @author Marc Chung <mchung@gmail.com>
 */
public class EvdbServiceFactory extends AbstractServiceFactory<EvdbLoginProvider> {

    private final IEvdbConfig mConfig;

    public EvdbServiceFactory(final IEvdbConfig pConfig) {

        mConfig = pConfig;

    }

    public BasicAuthenticationService getBasicAuthenticationService() {

        final BasicAuthenticationService authService = new BasicAuthenticationService();
        authService.setEvdbConfig(mConfig);
        return authService;
    }

    public DigestAuthenticationService getDigestAuthenticationService() {

        final DigestAuthenticationService authService = new DigestAuthenticationService();
        authService.setEvdbConfig(mConfig);
        return authService;
    }

    public VenuesSearchService getVenuesSearchService() {

        final VenuesSearchService venueSearch = new VenuesSearchService();
        venueSearch.setEvdbConfig(mConfig);
        venueSearch.setAppKey(getCredentials().getAppKey());
        return venueSearch;
    }
}

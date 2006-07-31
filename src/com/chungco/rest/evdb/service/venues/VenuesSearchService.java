
package com.chungco.rest.evdb.service.venues;

import com.chungco.rest.evdb.service.AbstractEvdbService;

// TODO Complete implementation
public class VenuesSearchService extends AbstractEvdbService<VenuesSearchResult> {

    /** The search keywords. String */
    protected final static String KEY_KEYWORDS    = "keywords";

    /**
     * A location name to use in filtering the search results. Locations in the
     * form "San Diego", "San Diego, TX", "London, United Kingdom", and
     * "Calgary, Alberta, Canada" are accepted. String
     */
    protected final static String KEY_LOCATION    = "location";

    /**
     * If count_only is set, an abbreviated version of the output will be
     * returned. Only total_items and search_time elements are included in the
     * result. boolean
     */
    protected final static String KEY_COUNT_ONLY  = "count_only";

    /** The desired number of results per "page". integer */
    protected final static String KEY_PAGE_SIZE   = "page_size";

    /** The desired "page" number. integer */
    protected final static String KEY_PAGE_NUMBER = "page_number";

    @Override
    protected VenuesSearchResult doParseXml(String pXmlStr) {

        return new VenuesSearchResult(pXmlStr);
    }

    @Override
    protected String getEndpointURL() {

        return getEvdbConfig().getHostName() + "/rest/venues/search?" + makeGET(KEY_APP_KEY, KEY_USERNAME, KEY_USER_KEY, KEY_KEYWORDS, KEY_LOCATION, KEY_COUNT_ONLY, KEY_PAGE_SIZE, KEY_PAGE_NUMBER);
    }

    public void setKeywords(final String pKeywords) {

        setParam(KEY_KEYWORDS, pKeywords);
    }

    public void setLocation(final String pLocation) {

        setParam(KEY_LOCATION, pLocation);
    }

    public void setCountOnly(final boolean pCountOnly) {

        setParam(KEY_COUNT_ONLY, Boolean.toString(pCountOnly));
    }

    public void setPageSize(final int pPgSize) {

        setParam(KEY_PAGE_SIZE, Integer.toString(pPgSize));
    }

    public void setPageNumber(final int pPgNo) {

        setParam(KEY_PAGE_NUMBER, Integer.toString(pPgNo));
    }

}

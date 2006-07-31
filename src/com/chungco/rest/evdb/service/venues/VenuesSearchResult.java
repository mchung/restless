
package com.chungco.rest.evdb.service.venues;

import com.chungco.rest.evdb.service.AbstractEvdbResult;

// TODO Complete results implementation
public class VenuesSearchResult extends AbstractEvdbResult {

    @Override
    public Boolean success() {

        return getXmlResponse() != null;

    }

    public VenuesSearchResult(final String pXmlResponse) {

        setXmlResponse(pXmlResponse);
    }

    // <total_items>4</total_items>
    // <page_size>10</page_size>
    // <page_count>1</page_count>
    // <page_number>1</page_number>
    // <page_items>4</page_items>
    // <first_item>1</first_item>
    // <last_item>4</last_item>
    // <search_time>0.004</search_time>

}

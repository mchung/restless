/**
 * 
 */
package com.chungco.rest.boxnet.model;

public enum Share {
    SHARE(1, "share"), UNSHARE(0, "unshare"), IGNORE(0, "ignore");

    final int    i;

    final String m;

    Share(final int x, final String p) {

        i = x;
        m = p;
    }

    @Override
    public String toString() {

        return m;
    }

}
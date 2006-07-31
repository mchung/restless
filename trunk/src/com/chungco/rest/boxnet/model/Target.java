/**
 * 
 */
package com.chungco.rest.boxnet.model;

public enum Target {
    FILE(1, "file"), FOLDER(0, "folder");

    final int    i;

    final String m;

    Target(final int x, final String p) {

        i = x;
        m = p;
    }

    @Override
    public String toString() {

        return m;
    }

}
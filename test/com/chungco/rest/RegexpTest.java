
package com.chungco.rest;

import java.util.ArrayList;
import java.util.List;

import com.chungco.core.KeyValuePair;
import com.chungco.rest.RestUtils;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RegexpTest extends TestCase {

    private final static String                      regexp = RestUtils.getEnRE();

    private final List<KeyValuePair<String, String>> before_after;

    public RegexpTest() {

        before_after = new ArrayList<KeyValuePair<String, String>>();
    }

    public void testRegexp() {

        final String REPLACE = "REPLACE";
        before_after.add(new KeyValuePair<String, String>("Welcome @name@", "Welcome REPLACE"));
        before_after.add(new KeyValuePair<String, String>("Welcome mchung@gmail.com and bob@gmail.com", "Welcome mchung@gmail.com and bob@gmail.com"));
        before_after.add(new KeyValuePair<String, String>("Welcome mchung@gmail.com.and.bob@gmail.com", "Welcome mchungREPLACEgmail.com"));

        for (final KeyValuePair<String, String> ok : before_after) {
            final String replString = ok.key();
            final String newString = replString.replaceAll(regexp, REPLACE);
            final boolean equals = ok.value().equals(newString);
            if (!equals) {
                System.out.println("Wanted: [" + ok.value() + "] Got: [" + newString + "]");
                Assert.fail();
            }

        }
    }

    public void testRegexp2() {

        final String orig = "<xml><action>account_tree</action><folder_id>@folder_id_here@</folder_id><one_level>@one_level@</one_level></xml>";
        final String next = "<xml><action>account_tree</action><folder_id></folder_id><one_level></one_level></xml>";
        final String done = orig.replaceAll(RestUtils.getEnRE(), "");

        Assert.assertEquals(next, done);
        System.out.println(done);

    }

}

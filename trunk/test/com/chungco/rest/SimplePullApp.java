
package com.chungco.rest;

import java.io.IOException;
import java.io.StringReader;

import javolution.xml.pull.XmlPullParser;
import javolution.xml.pull.XmlPullParserException;
import javolution.xml.pull.XmlPullParserImpl;

// This is not a Real Test Case
public class SimplePullApp {

    public static void main(String args[]) throws XmlPullParserException, IOException {

        XmlPullParser xpp = new XmlPullParserImpl();

        xpp.setInput(new StringReader("<foo>Hello World!</foo>"));
        int eventType = xpp.getEventType();
        boolean done = false;
        while (!done) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("Start document");
            } else if (eventType == XmlPullParser.END_DOCUMENT) {
                System.out.println("End document");
                done = true;
            } else if (eventType == XmlPullParser.START_TAG) {
                System.out.println("Start tag " + xpp.getName());
            } else if (eventType == XmlPullParser.END_TAG) {
                System.out.println("End tag " + xpp.getName());
            } else if (eventType == XmlPullParser.TEXT) {
                System.out.println("Text " + xpp.getText());
            }
            if (!done)
                eventType = xpp.next();
        }
    }
}
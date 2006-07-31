
package com.chungco.core.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.chungco.rest.boxnet.BoxService;
import com.chungco.rest.boxnet.service.AuthorizationResult;
import com.chungco.rest.boxnet.service.MockBoxConfig;
import com.chungco.rest.boxnet.service.MockLoginPassword;

/**
 * A very manual upload example. This is not a unit test
 */
public class MultiPartFormOutputStreamTest {

    // TODO Enable uploads of really, really large files, i.e. > 1Gb

    private final static MockLoginPassword provider = new MockLoginPassword();

    private final static String            HOST     = new MockBoxConfig().getBoxNetHost();

    public static void main(final String[] pArgs) throws Exception {

        if (pArgs.length == 0) {
            System.out.println("To run, ensure arg[0]=C:\\absolute\\path\\to\\file.name");
            // Hint: In Eclipse, Debug > Arguments > Variables... (Program
            // Arguments section) > "file_prompt"
            System.exit(1);
        }

        final BoxService box = new BoxService();
        final AuthorizationResult result = box.login(provider.getUsername(), provider.getPassword());
        final String TARGET_URL = HOST + "/ping/upload/" + result.getSID();

        final URL url = new URL(TARGET_URL);
        final String boundary = MultiPartFormOutputStream.createBoundary();
        URLConnection urlConn = MultiPartFormOutputStream.createConnection(url);
        urlConn.setRequestProperty("Accept", "*/*");
        urlConn.setRequestProperty("Content-Type", MultiPartFormOutputStream.getContentType(boundary));
        urlConn.setRequestProperty("Connection", "Keep-Alive");
        urlConn.setRequestProperty("Cache-Control", "no-cache");
        final MultiPartFormOutputStream out = new MultiPartFormOutputStream(urlConn.getOutputStream(), boundary);
        out.writeFile("new_file1", "application/octet-stream", new File(pArgs[0]));
        out.writeField("location", "0");
        out.writeField("upload_files", "Upload Files");
        out.close();

        final BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        String line = "";
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
        in.close();
    }
}

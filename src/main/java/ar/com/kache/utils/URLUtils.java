package ar.com.kache.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sperruolo on 7/24/16.
 */
public class URLUtils {
    public static InputStream get(String anURL) throws IOException {
        System.out.println("Processing: " + anURL);
        URL url = new URL(anURL);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/51.0.2704.79 Chrome/51.0.2704.79 Safari/537.36");
        return conn.getInputStream();
    }
}

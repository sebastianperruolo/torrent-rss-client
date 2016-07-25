package ar.com.kache.executors;

import ar.com.kache.model.FeedItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created by sperruolo on 7/24/16.
 */
public class TransmissionRemoteExecutor extends FeedItemExecutor {
    private final String append;

    public TransmissionRemoteExecutor(String append) {
        this.append = append;
    }

    public TransmissionRemoteExecutor() {
        this.append = "";
    }

    @Override
    public void execute(FeedItem elem) {
        //"/usr/bin/transmission-remote --add "%s"
        String soCommand = "/usr/bin/transmission-remote --add %s %s";
        String urlString = (elem.enclosure.isPresent()? elem.enclosure.get().url : elem.link);

        try {
            URL url = new URL(urlString);
            urlString = url.getProtocol() + "://" + url.getHost() + url.getPath();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String command = String.format(soCommand, urlString, this.append);
        try {
            System.out.println("torrent: " + urlString);
            System.out.println("append: " + this.append);
            System.out.println("Executing command: " + command);

            Process tr = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(
                    tr.getInputStream()));

            String line;

            while ((line = input.readLine()) != null)
            {
                System.out.println(line);
            }
            tr.waitFor();
            System.out.println(tr.exitValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

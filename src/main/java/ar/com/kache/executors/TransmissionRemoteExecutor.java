package ar.com.kache.executors;

import ar.com.kache.model.FeedItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by sperruolo on 7/24/16.
 */
public class TransmissionRemoteExecutor extends FeedItemExecutor {
    @Override
    public void execute(FeedItem elem) {
        //"/usr/bin/transmission-remote --add \"%s\"";
        String soCommand = "/usr/bin/transmission-remote --add %s";

        String command = String.format(soCommand, elem.link);
        try {
            System.out.println("Executing command: " + command);
            Process tr = Runtime.getRuntime().exec( command );
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

package ar.com.kache.executors;

import ar.com.kache.model.FeedItem;

/**
 * Created by sperruolo on 7/24/16.
 */
public class FeedItemExecutor {
    public void execute(FeedItem elem) {
        System.out.println("Adding torrent: " + elem.link);
    }
}

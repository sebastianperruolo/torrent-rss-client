package ar.com.kache.filters;

import ar.com.kache.model.FeedItem;

/**
 * Created by sperruolo on 7/24/16.
 */
public interface FeedItemFilter {
    boolean valid(FeedItem elem);
}

package ar.com.kache.filters;

import ar.com.kache.model.FeedItem;

/**
 * Created by sperruolo on 7/24/16.
 */
public class NullFeedItemFilter implements FeedItemFilter {
    private final boolean value;

    public NullFeedItemFilter(boolean value) {
        this.value = value;
    }

    @Override
    public boolean valid(FeedItem elem) {
        return value;
    }
}

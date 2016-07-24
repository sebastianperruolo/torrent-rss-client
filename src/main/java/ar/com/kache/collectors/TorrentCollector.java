package ar.com.kache.collectors;

import ar.com.kache.executors.FeedItemExecutor;
import ar.com.kache.executors.TransmissionRemoteExecutor;
import ar.com.kache.filters.FeedItemFilter;
import ar.com.kache.filters.NullFeedItemFilter;
import ar.com.kache.model.FeedItem;

/**
 * Created by sperruolo on 7/24/16.
 */
public class TorrentCollector implements FeedCollector<FeedItem> {
    private final FeedItemFilter filter;
    private final FeedItemExecutor executor = new TransmissionRemoteExecutor();

    public TorrentCollector() {
        this.filter = new NullFeedItemFilter(true);
    }

    public TorrentCollector(FeedItemFilter filter) {
        this.filter = filter;
    }

    @Override
    public void add(FeedItem elem) {
        //System.out.println(elem.toString());
        if (filter.valid(elem)) {
            executor.execute(elem);
        }
    }
}

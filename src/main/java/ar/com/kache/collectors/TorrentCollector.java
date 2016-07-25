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
    private final FeedItemExecutor executor;

    public TorrentCollector() {
        this.filter = new NullFeedItemFilter(true);
        this.executor = new TransmissionRemoteExecutor();
    }

    public TorrentCollector(final FeedItemFilter filter, final String byPass) {
        this.filter = filter;
        this.executor = new TransmissionRemoteExecutor(byPass == null? "" : byPass);
    }

    @Override
    public void add(FeedItem elem) {
        //System.out.println(elem.toString());
        if (filter.valid(elem)) {
            executor.execute(elem);
        }
    }
}

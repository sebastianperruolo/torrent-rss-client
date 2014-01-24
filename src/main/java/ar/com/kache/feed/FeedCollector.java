package ar.com.kache.feed;

import it.sauronsoftware.feed4j.bean.FeedItem;

public interface FeedCollector {

	void collect(FeedItem feedItem);

}

package ar.com.kache.filters;

import it.sauronsoftware.feed4j.bean.FeedItem;
import ar.com.kache.config.ConfigFeed;

public interface IFilterStrategy {

	boolean accept(ConfigFeed configFeed, FeedItem feedItem);

}

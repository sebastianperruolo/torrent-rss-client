package ar.com.kache.filters;

import it.sauronsoftware.feed4j.bean.FeedItem;
import ar.com.kache.config.ConfigFeed;

public class NullFilterStrategy implements IFilterStrategy {

	@Override
	public boolean accept(ConfigFeed configFeed, FeedItem feedItem) {
		return true;
	}

}

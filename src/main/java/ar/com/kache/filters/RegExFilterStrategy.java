package ar.com.kache.filters;

import it.sauronsoftware.feed4j.bean.FeedItem;
import ar.com.kache.config.ConfigFeed;

public class RegExFilterStrategy implements IFilterStrategy {
	
	public static final String CODE = "regex";
	
	@Override
	public boolean accept(ConfigFeed configFeed, FeedItem feedItem) {
		for (String regex : configFeed.getFilters()) {
			if (feedItem.getTitle().matches(regex)) {
				return true;
			}
		}
		
		return false;
	}
}

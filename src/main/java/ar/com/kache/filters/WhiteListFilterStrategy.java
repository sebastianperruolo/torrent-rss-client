package ar.com.kache.filters;

import it.sauronsoftware.feed4j.bean.FeedItem;
import ar.com.kache.config.ConfigFeed;

/**
 * If feed item title starts with any {@link ConfigFeed#getFilters()}
 * the item is accept.
 * 
 * @author sebastianperruolo
 */
public class WhiteListFilterStrategy implements IFilterStrategy {

	public static final String CODE = "whitelist";

	@Override
	public boolean accept(ConfigFeed configFeed, FeedItem feedItem) {
		//if feed
		for (String prefix : configFeed.getFilters()) {
			if (feedItem.getTitle().startsWith(prefix)) {
				return true;
			}
		}
		
		return false;
	}

}

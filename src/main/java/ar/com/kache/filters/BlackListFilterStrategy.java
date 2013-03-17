package ar.com.kache.filters;

import it.sauronsoftware.feed4j.bean.FeedItem;
import ar.com.kache.config.ConfigFeed;

/**
 * If feed item title starts with any {@link ConfigFeed#getFilters()}
 * the item is denied.
 * 
 * @author sebastianperruolo
 */
public class BlackListFilterStrategy implements IFilterStrategy {

	public static final String CODE = "blacklist";;

	@Override
	public boolean accept(ConfigFeed configFeed, FeedItem feedItem) {
		//if feed
		for (String prefix : configFeed.getFilters()) {
			if (feedItem.getTitle().startsWith(prefix)) {
				return false;
			}
		}
		
		return true;
	}

}

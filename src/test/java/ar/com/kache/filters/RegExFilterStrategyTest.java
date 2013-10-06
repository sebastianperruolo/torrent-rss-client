package ar.com.kache.filters;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import it.sauronsoftware.feed4j.bean.FeedItem;

import org.junit.Test;

import ar.com.kache.config.ConfigFeed;

public class RegExFilterStrategyTest {

	@Test
	public void test() {
		IFilterStrategy filter = new RegExFilterStrategy();
		ConfigFeed configFeed = new ConfigFeed();
		configFeed.setFilters(Arrays.asList("(.+)S01E01(.+)HDTV(.+)"));
		FeedItem feedItem = new FeedItem();
		feedItem.setTitle("A New Show S01E01 720p HDTV x264-TEST");
		
		assertTrue(filter.accept(configFeed, feedItem));
		feedItem = new FeedItem();
		feedItem.setTitle("A New Show S01E01 HDTV x264-TEST");
		assertTrue(filter.accept(configFeed, feedItem));
		
		assertTrue(filter.accept(configFeed, feedItem));
		feedItem = new FeedItem();
		feedItem.setTitle("A New Show S01e01 HDTV x264-TEST");
		assertTrue(!filter.accept(configFeed, feedItem));
	}

}

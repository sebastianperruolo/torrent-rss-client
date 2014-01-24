package ar.com.kache.feed;

import it.sauronsoftware.feed4j.bean.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class ListFeedCollector implements FeedCollector {
	private List<FeedItem> list = new ArrayList<FeedItem>();
	
	@Override
	public void collect(FeedItem feedItem) {
		list.add(feedItem);
	}

	public List<FeedItem> getList() {
		return list;
	}
}

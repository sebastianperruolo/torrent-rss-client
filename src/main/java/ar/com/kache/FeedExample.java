package ar.com.kache;

import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedHeader;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.net.URL;

public class FeedExample {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://yify-torrents.com/rss/0/All/All/0");

		Feed feed = FeedParser.parse(url);
		
		System.out.println("** HEADER **");
		FeedHeader header = feed.getHeader();
		System.out.println("Title: " + header.getTitle());
		System.out.println("Link: " + header.getLink());
		System.out.println("Description: " + header.getDescription());
		System.out.println("Language: " + header.getLanguage());
		System.out.println("PubDate: " + header.getPubDate());

		System.out.println("** ITEMS **");
		int items = feed.getItemCount();
		for (int i = 0; i < items; i++) {
			FeedItem item = feed.getItem(i);
			System.out.println("================= Title: " + item.getTitle());
			System.out.println("Link: " + item.getLink());
			System.out.println("Plain text description: "
					+ item.getDescriptionAsText());
			System.out.println("HTML description: "
					+ item.getDescriptionAsHTML());
			System.out.println("PubDate: " + item.getPubDate());
		}

	}

}

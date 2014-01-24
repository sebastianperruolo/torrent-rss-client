package ar.com.kache.feed;

import static org.junit.Assert.assertEquals;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class FeedXmlParserTest {

	@Test
	public void testGetFeedItemList() {
		//Argenteam
		List<FeedItem> feedItemList = FeedXmlParser.getFeedItemList(new File("src/test/resources/junit/argenteam.xml"));
		
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.torrent",feedItemList.get(0).getLink());
		assertEquals("Video1",feedItemList.get(0).getTitle());
		assertEquals("Video 1",feedItemList.get(0).getDescriptionAsText());
		
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.torrent",feedItemList.get(1).getLink());
		assertEquals("Video2",feedItemList.get(1).getTitle());
		assertEquals("Video 2",feedItemList.get(1).getDescriptionAsText());
		
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.torrent",feedItemList.get(2).getLink());
		assertEquals("Video3",feedItemList.get(2).getTitle());
		assertEquals("Video 3",feedItemList.get(2).getDescriptionAsText());
		
		//Kickass
		feedItemList = FeedXmlParser.getFeedItemList(new File("src/test/resources/junit/kickasstorrent.xml"));
		
		assertEquals("Video1",feedItemList.get(0).getTitle());
		assertEquals("Video 1",feedItemList.get(0).getDescriptionAsText());
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.html",feedItemList.get(0).getLink());
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.html",feedItemList.get(0).getGUID());
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.torrent",feedItemList.get(0).getEnclosure(0).getURL().toString());
		
		assertEquals("Video2",feedItemList.get(1).getTitle());
		assertEquals("Video 2",feedItemList.get(1).getDescriptionAsText());
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.html",feedItemList.get(1).getLink());
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.html",feedItemList.get(1).getGUID());
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.torrent",feedItemList.get(1).getEnclosure(0).getURL().toString());
		
		assertEquals("Video3",feedItemList.get(2).getTitle());
		assertEquals("Video 3",feedItemList.get(2).getDescriptionAsText());
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.html",feedItemList.get(2).getLink());
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.html",feedItemList.get(2).getGUID());
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.torrent",feedItemList.get(2).getEnclosure(0).getURL().toString());
		
		//magnetlink
		feedItemList = FeedXmlParser.getFeedItemList(new File("src/test/resources/junit/magnetlink.xml"));
		
		assertEquals("magnet:?xt=urn:btih:0000000000001111111111112222222222222222&dn=Video1",feedItemList.get(0).getLink());
		assertEquals("Video1",feedItemList.get(0).getTitle());
		assertEquals("Video 1",feedItemList.get(0).getDescriptionAsText());
		
		assertEquals("magnet:?xt=urn:btih:0000000000001111111111112222222222222222&dn=Video2",feedItemList.get(1).getLink());
		assertEquals("Video2",feedItemList.get(1).getTitle());
		assertEquals("Video 2",feedItemList.get(1).getDescriptionAsText());
		
		//thepiratebay
		feedItemList = FeedXmlParser.getFeedItemList(new File("src/test/resources/junit/thepiratebay.xml"));
		
		assertEquals("magnet:?xt=urn:btih:0000000000001111111111112222222222222222&dn=Video1",feedItemList.get(0).getLink());
		assertEquals("Video1",feedItemList.get(0).getTitle());
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.html",feedItemList.get(0).getComments().toString());
		
		assertEquals("magnet:?xt=urn:btih:0000000000001111111111112222222222222222&dn=Video2",feedItemList.get(1).getLink());
		assertEquals("Video2",feedItemList.get(1).getTitle());
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.html",feedItemList.get(1).getComments().toString());
		
		assertEquals("magnet:?xt=urn:btih:0000000000001111111111112222222222222222&dn=Video3",feedItemList.get(2).getLink());
		assertEquals("Video3",feedItemList.get(2).getTitle());
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.html",feedItemList.get(2).getComments().toString());
		
		//yify
		feedItemList = FeedXmlParser.getFeedItemList(new File("src/test/resources/junit/yifytorrents.xml"));
		
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.html",feedItemList.get(0).getLink());
		assertEquals("Video1",feedItemList.get(0).getTitle());
		assertEquals("Video 1",feedItemList.get(0).getDescriptionAsText());
		assertEquals("http://kache.com.ar/torrent-rss-client/video1.torrent",feedItemList.get(0).getEnclosure(0).getURL().toString());
		
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.html",feedItemList.get(1).getLink());
		assertEquals("Video2",feedItemList.get(1).getTitle());
		assertEquals("Video 2",feedItemList.get(1).getDescriptionAsText());
		assertEquals("http://kache.com.ar/torrent-rss-client/video2.torrent",feedItemList.get(1).getEnclosure(0).getURL().toString());
		
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.html",feedItemList.get(2).getLink());
		assertEquals("Video3",feedItemList.get(2).getTitle());
		assertEquals("Video 3",feedItemList.get(2).getDescriptionAsText());
		assertEquals("http://kache.com.ar/torrent-rss-client/video3.torrent",feedItemList.get(2).getEnclosure(0).getURL().toString());
		
		
	}

	@Test
	public void testCollectFeedItem() {
		//fail("Not yet implemented");
	}

}

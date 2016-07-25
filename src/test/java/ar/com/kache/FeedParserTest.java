package ar.com.kache;

import ar.com.kache.collectors.ListCollector;
import ar.com.kache.handlers.FeedATHandler;
import ar.com.kache.model.FeedItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sperruolo on 7/24/16.
 */
public class FeedParserTest {

    @Test
    public void testATParse() throws Exception {
        ListCollector<FeedItem> feedItems = new ListCollector<>();
        FeedATHandler handler = new FeedATHandler(feedItems);
        // some test logic
        FeedSAXParser.parse(ClassLoader.getSystemResourceAsStream("feeds/atfeed.xml"), handler);
        assertEquals(1, feedItems.getList().size());
    }

    @Test
    public void testLTParse() throws Exception {
        ListCollector<FeedItem> feedItems = new ListCollector<>();
        FeedATHandler handler = new FeedATHandler(feedItems);
        // some test logic
        FeedSAXParser.parse(ClassLoader.getSystemResourceAsStream("feeds/ltfeed.xml"), handler);
        assertEquals(1, feedItems.getList().size());
        FeedItem feedItem = feedItems.getList().get(0);
        assertEquals("http://www.limetorrents.cc/Ubuntu-16-04-LTS-  039 Xenial-Xerus  039 -Desktop-PC-(64-bit)-torrent-7765467.html", feedItem.link);
        assertEquals("http://itorrents.org/torrent/59FF6CA855501A8DBB19FB2F51BA714205F2C430.torrent?title=Ubuntu-16-04-LTS-  039 Xenial-Xerus  039 -Desktop-PC-(64-bit)", feedItem.enclosure.get().url);
        System.out.println(feedItem.toString());
    }
}

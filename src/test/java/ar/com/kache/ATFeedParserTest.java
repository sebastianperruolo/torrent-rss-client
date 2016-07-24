package ar.com.kache;

import ar.com.kache.collectors.ListCollector;
import ar.com.kache.handlers.FeedATHandler;
import ar.com.kache.model.FeedItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by sperruolo on 7/24/16.
 */
public class ATFeedParserTest {

    @Test
    public void testParse() throws Exception {
        ListCollector<FeedItem> feedItems = new ListCollector<>();
        FeedATHandler handler = new FeedATHandler(feedItems);
        // some test logic
        FeedSAXParser.parse(ClassLoader.getSystemResourceAsStream("feeds/atfeed.xml"), handler);
        assertEquals(1, feedItems.getList().size());
    }
}

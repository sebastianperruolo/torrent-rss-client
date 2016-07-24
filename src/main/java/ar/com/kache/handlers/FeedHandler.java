package ar.com.kache.handlers;

import ar.com.kache.collectors.FeedCollector;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by sperruolo on 7/24/16.
 */
public abstract class FeedHandler extends DefaultHandler {
    protected final FeedCollector collector;

    public FeedHandler(FeedCollector collector) {
        this.collector = collector;
    }

    public FeedCollector getCollector() {
        return collector;
    }

    protected FeedHandlerContent content = new FeedHandlerContent();

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content.append(String.copyValueOf(ch, start, length).trim());
    }
}

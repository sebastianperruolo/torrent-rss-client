package ar.com.kache.handlers;

import ar.com.kache.collectors.FeedCollector;
import ar.com.kache.collectors.TorrentCollector;
import ar.com.kache.model.FeedItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * The Handler for SAX Events.
 */
public class FeedATHandler extends FeedHandler {

    private FeedItem.FeedItemBuilder itemBuilder;


    public FeedATHandler(FeedCollector collector) {
        super(collector);
    }

    @Override
    //Triggered when the start of tag is found.
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {
        content.start();
        switch (qName) {
            //Create a new FeedItem object when the start tag is found
            case "item":
                itemBuilder = new FeedItem.FeedItemBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {

        if (itemBuilder == null) {
            return;
        }
        switch (qName) {
            //Add the employee to list once end tag is found
            case "item":
                collector.add(itemBuilder.build());
                break;
            //For all other end tags the employee has to be updated.
            case "title":
                itemBuilder.title(content.text());
                break;
            case "link":
                itemBuilder.link(content.text());
                break;
            case "description":
                itemBuilder.description(content.text());
                break;
            case "pubDate":
                itemBuilder.pubDate(content.text());
                break;
        }
    }


}

package ar.com.kache;

import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ar.com.kache.handlers.FeedHandler;

public class FeedSAXParser {

    public static void parse(InputStream feed, FeedHandler handler) throws Exception {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        parser.parse(feed, handler);
    }
}


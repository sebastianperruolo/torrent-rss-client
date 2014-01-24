package ar.com.kache.feed;

import it.sauronsoftware.feed4j.bean.FeedItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class FeedXmlParser {

	public static List<FeedItem> getFeedItemList(File feedFile) {
		ListFeedCollector collector = new ListFeedCollector();
		collectFeedItem(feedFile, collector);
		return collector.getList();
	}
	
	public static void collectFeedItem(File feedFile, FeedCollector collector) {
		try {
			parseXml(new FileInputStream(feedFile), collector);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void parseXml(InputStream in, FeedCollector collector) {
		//Create a empty link of users initially
		try {
			//Create default handler instance
			FeedParserHandler handler = new FeedParserHandler(collector);

			//Create parser from factory
			XMLReader parser = XMLReaderFactory.createXMLReader();

			//Register handler with parser
			parser.setContentHandler(handler);

			//Create an input source from the XML input stream
			InputSource source = new InputSource(in);

			//parse the document
			parser.parse(source);


		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}
}

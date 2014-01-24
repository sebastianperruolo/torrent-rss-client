package ar.com.kache.feed;

import it.sauronsoftware.feed4j.bean.FeedEnclosure;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FeedParserHandler extends DefaultHandler {
	// This is the list which shall be populated while parsing the XML.
	private FeedCollector collector = null;

	// As we complete one user block in XML, we will push the User instance in
	// userList
	private Stack<FeedItem> objectStack = new Stack<FeedItem>();
	
	private StringBuilder valueBuilder = null;
	
	public FeedParserHandler(FeedCollector feedList) {
		this.collector = feedList;
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {

		// If this is start of 'user' element then prepare a new User instance
		// and push it in object stack
		if ("item".equals(qName)) {
			// New User instance
			FeedItem feedItem = new FeedItem();

			this.objectStack.push(feedItem);
		} else if ("enclosure".equals(qName)) {
			//FeedEnclosure System.out.println(attributes.getValue(0));
			FeedEnclosure enclosure = buildFeedEnclosure(attributes);
			this.objectStack.peek().addEnclosure(enclosure);
		}
		valueBuilder = new StringBuilder();
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		// User instance has been constructed so pop it from object stack and
		// push in userList
		if ("item".equals(qName)) {
			FeedItem feedItem = this.objectStack.pop();
			this.collector.collect(feedItem);
		} else if (!this.objectStack.empty()) {
			if ("title".equals(qName)) {
				this.objectStack.peek().setTitle(valueBuilder.toString());
			} else if ("link".equals(qName)) {
				this.objectStack.peek().setLink(valueBuilder.toString());
			} else if ("comments".equals(qName)) {
				this.objectStack.peek().setComments(buildURL(valueBuilder.toString()));
			} else if ("description".equals(qName)) {
				this.objectStack.peek().setDescriptionAsText(valueBuilder.toString());
			} else if ("guid".equals(qName)) {
				this.objectStack.peek().setGUID(valueBuilder.toString());
			}
		}
	}

	/**
	 * This will be called everytime parser encounter a value node
	 * */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length);

		if (value.length() == 0) {
			return; // ignore white space
		}
		
		valueBuilder.append(value);
	}

	private FeedEnclosure buildFeedEnclosure(Attributes attributes) {
		FeedEnclosure enclosure = new FeedEnclosure();
		enclosure.setURL(buildURL(attributes.getValue("url")));
		enclosure.setLength(buildLong(attributes.getValue("length")));
		enclosure.setMimeType(attributes.getValue("type"));
		return enclosure;
	}
	
	private URL buildURL(String urlValue) {
		try {
			return new URL(urlValue);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private long buildLong(String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
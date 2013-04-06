package it.sauronsoftware.feed4j.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {

	    private XMLReader xmlReader;

	    public MyContentHandler(XMLReader xmlReader) {
	        this.xmlReader = xmlReader;
	    }

	    public void startElement(String uri, String localName, String qName,
	            Attributes atts) throws SAXException {
	    	System.out.println(qName);
	        if ("sodium".equals(qName)) {
	            xmlReader.setContentHandler(new IgnoringContentHandler(xmlReader,
	                    this));
	        } else {
	            System.out.println("START " + qName);
	        }
	    }

	    public void endElement(String uri, String localName, String qName)
	            throws SAXException {
	        System.out.println("END " + qName);
	    }

	    public void characters(char[] ch, int start, int length)
	            throws SAXException {
	        System.out.println(new String(ch, start, length));
	    }

	}
package it.sauronsoftware.feed4j.utils;

import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class XMLParseUtils {
	public static Document read(InputStream stream) throws DocumentException, SAXException, ParserConfigurationException {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		javax.xml.parsers.SAXParser parser = factory.newSAXParser();
		org.xml.sax.XMLReader reader = parser.getXMLReader();
		reader.setContentHandler(new MyContentHandler(reader));
		
		SAXReader saxReader = new SAXReader(reader);
		return saxReader.read(stream);
	}
}

package ar.com.kache.formats;

import it.sauronsoftware.feed4j.bean.FeedItem;

import java.net.MalformedURLException;
import java.net.URL;

import ar.com.kache.config.AppConfiguration;


public class LinkFormatManager {
	private AppConfiguration appConfiguration;
	public LinkFormatManager(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
	}
	
	public ILinkFormat getLinkFormatFor(FeedItem feedItem) {
		if (feedItem.getEnclosureCount() > 0) {
			return new TorrentFileLinkFormat(appConfiguration, feedItem.getEnclosure(0).getURL(), feedItem.getTitle());
		}
		String link = feedItem.getLink();
		if (link == null) {
			return null;
		}
		if (link.startsWith("magnet:")) {
			return new MagnetLinkFormat(appConfiguration, link, feedItem.getTitle());
		}
		try {
			URL url = new URL(link);
			URL url2 = new URL("http", url.getHost(), url.getPort(), url.getFile());
			//in case URL is a https address
			return new TorrentFileLinkFormat(appConfiguration, url2, feedItem.getTitle());
		} catch (MalformedURLException e) {
			
		}
		
		// TODO Auto-generated method stub
		System.out.println("Can't build URL from '" + feedItem.getLink() + "'");
		return null;
	}
}

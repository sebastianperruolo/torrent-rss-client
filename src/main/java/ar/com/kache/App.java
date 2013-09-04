package ar.com.kache;

import it.sauronsoftware.feed4j.FeedIOException;
import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.FeedXMLParseException;
import it.sauronsoftware.feed4j.UnsupportedFeedException;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.config.ConfigFeed;
import ar.com.kache.filters.FilterStrategyManager;
import ar.com.kache.filters.IFilterStrategy;
import ar.com.kache.formats.ILinkFormat;
import ar.com.kache.formats.LinkFormatManager;
import ar.com.kache.history.FileHistory;
import ar.com.kache.history.IHistory;
import ar.com.kache.utils.FileUtils;

/**
 * Main App class
 *
 */
public class App {
	private IHistory history;
	private AppConfiguration appConfiguration;
	private LinkFormatManager linkFormatManager;
	
	
	public App(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
		this.history = new FileHistory(appConfiguration.getHistoryFile());
		this.linkFormatManager = new LinkFormatManager(this.appConfiguration);
	}
	
	public void execute() {
		for (ConfigFeed configFeed : appConfiguration.getConfiguration().getConfigFeeds()) {
			System.out.println("Feed " + configFeed.getTitle());
			try {
				process(configFeed);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void process(ConfigFeed configFeed) throws FeedIOException, FeedXMLParseException, UnsupportedFeedException, IOException {
		URL url = new URL(configFeed.getUrl());
		File feedFile = new File(AppConfiguration.appLog, configFeed.getTitle() + ".xml");
		
		FileUtils.download(url, feedFile);
		Feed feed = FeedParser.parse(url, new FileInputStream(feedFile));
		
		for (FeedItem feedItem : feed.getItems()) {
			process(configFeed, feedItem);
		}
	}
	
	private void process(ConfigFeed configFeed, FeedItem feedItem) {
		IFilterStrategy filterStrategy = FilterStrategyManager.getInstance().getFilterFor(configFeed.getFilterStrategy());
		if (!filterStrategy.accept(configFeed, feedItem)) {
			System.out.println("\n========Feed '" + feedItem.getTitle() + "' was rejected");
			return;
		} 

		System.out.println("\n========Feed '" + feedItem.getTitle() + "' was accepted");
//		URL url = getURL(feedItem);
		ILinkFormat linkStrategy = linkFormatManager.getLinkFormatFor(feedItem);
		if (linkStrategy == null) {
			return;
		}
		
//		String fileName = feedItem.getTitle() + ".torrent";
		
//		File tempTorrent = new File(AppConfiguration.appTemp, fileName);
		
//		if (tempTorrent.isFile()) {
//			System.out.println("Torrent file '" + tempTorrent.getAbsolutePath() + "' already exists");
//			return;
//		}
		
		if (linkStrategy.existsTemp()) {
			return;
		}
		
//		try {
//			int code = FileUtils.getResponseCode(url);
//			if (code != 200) {
//				System.out.println("URL " + url.toString() + " return " + code + " code.");
//				return;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			//return;
//		}
//		
//		try {
//			FileUtils.download(url, tempTorrent);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}
		
		linkStrategy.download();
		
		if (!linkStrategy.existsTemp()) {
			return;
		}
//		String hash = FileUtils.hash(tempTorrent);
//		
//		if (hash == null) {
//			System.out.println("Can't get hash for file '" + tempTorrent.getAbsolutePath() + "'. Is it a torrent file?");
//			return;
//		}

		
//		if (history.exists(hash)) {
//			System.out.println("File already exists");
//			return;
//		}
		if (history.exists(linkStrategy.getHash())) {
			System.out.println("File already exists");
			return;
		}
		
//		File watchdirTorrent = new File(appConfiguration.getConfiguration().getWatchDir(), tempTorrent.getName());
//		try {
//			FileUtils.copyFile(tempTorrent, watchdirTorrent);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}
		linkStrategy.sendToTorrentEngine();
		history.add(linkStrategy.getHash());
//		System.out.println("Added file " + watchdirTorrent.getAbsolutePath());
	}
	
//	private URL getURL(FeedItem feedItem) {
//		if (feedItem.getEnclosureCount() > 0) {
//			return feedItem.getEnclosure(0).getURL();
//		}
//		String link = feedItem.getLink();
//		if (link == null) {
//			return null;
//		}
//		try {
//			URL url = new URL(link);
//			//in case URL is a https address
//			return new URL("http", url.getHost(), url.getPort(), url.getFile());
//		} catch (MalformedURLException e) {
//			
//		}
//		return null;
//	}
}

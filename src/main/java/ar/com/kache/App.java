package ar.com.kache;

import it.sauronsoftware.feed4j.FeedIOException;
import it.sauronsoftware.feed4j.FeedParser;
import it.sauronsoftware.feed4j.FeedXMLParseException;
import it.sauronsoftware.feed4j.UnsupportedFeedException;
import it.sauronsoftware.feed4j.bean.Feed;
import it.sauronsoftware.feed4j.bean.FeedItem;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.config.ConfigFeed;
import ar.com.kache.filters.FilterStrategyManager;
import ar.com.kache.filters.IFilterStrategy;
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
	
	public App(AppConfiguration appConfiguration) {
		this.appConfiguration = appConfiguration;
		this.history = new FileHistory(appConfiguration.getHistoryFile());
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
	
	private void process(ConfigFeed configFeed) throws FeedIOException, FeedXMLParseException, UnsupportedFeedException, MalformedURLException {
		URL url = new URL(configFeed.getUrl());
		Feed feed = FeedParser.parse(url);
		
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
		URL url = getURL(feedItem);
		
		String fileName = url.getFile();
		if ((fileName == null) || (fileName.trim().equals(""))) {
			fileName = feedItem.getTitle() + ".torrent";
		}
		File tempTorrent = new File(AppConfiguration.appTemp, new File(url.getPath()).getName());
		
		if (tempTorrent.isFile()) {
			System.out.println("Torrent file '" + tempTorrent.getAbsolutePath() + "' already exists");
			return;
		}
		try {
			int code = FileUtils.getResponseCode(url);
			if (code != 200) {
				System.out.println("URL " + url.toString() + " return " + code + " code.");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		try {
			FileUtils.download(url, tempTorrent);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		String hash = null;
		try {
			hash = FileUtils.hash(tempTorrent);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		if (history.exists(hash)) {
			System.out.println("File already exists");
			return;
		}
		
		File watchdirTorrent = new File(appConfiguration.getConfiguration().getWatchDir(), tempTorrent.getName());
		try {
			FileUtils.copyFile(tempTorrent, watchdirTorrent);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		history.add(hash);
		System.out.println("Added file " + watchdirTorrent.getAbsolutePath());
	}
	
	private URL getURL(FeedItem feedItem) {
		if (feedItem.getEnclosureCount() > 0) {
			return feedItem.getEnclosure(0).getURL();
		}
		return feedItem.getLink();
	}
}

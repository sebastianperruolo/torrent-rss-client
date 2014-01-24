package ar.com.kache.torrent;

import it.sauronsoftware.feed4j.bean.FeedItem;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.config.ConfigFeed;
import ar.com.kache.feed.FeedCollector;
import ar.com.kache.feed.FeedXmlParser;
import ar.com.kache.filters.FilterStrategyManager;
import ar.com.kache.filters.IFilterStrategy;
import ar.com.kache.formats.ILinkFormat;
import ar.com.kache.formats.LinkFormatManager;
import ar.com.kache.history.IHistory;
import ar.com.kache.utils.FileUtils;

public class TorrentFeedCollector implements FeedCollector{
	
	private ConfigFeed configFeed;
	private IHistory history;
	private LinkFormatManager linkFormatManager;

	public TorrentFeedCollector(ConfigFeed configFeed, IHistory history, LinkFormatManager linkFormatManager) {
		this.configFeed = configFeed;
		this.history = history;
		this.linkFormatManager = linkFormatManager;
	}

	public void run() throws IOException {
		URL url = new URL(configFeed.getUrl());
		File feedFile = new File(AppConfiguration.appLog, configFeed.getTitle() + ".xml");
		
		FileUtils.download(url, feedFile);

		FeedXmlParser.collectFeedItem(feedFile, this);
		
	}

	@Override
	public void collect(FeedItem feedItem) {
		IFilterStrategy filterStrategy = FilterStrategyManager.getInstance().getFilterFor(configFeed.getFilterStrategy());
		if (!filterStrategy.accept(configFeed, feedItem)) {
			System.out.println("\n========Feed '" + feedItem.getTitle() + "' was rejected by filter");
			return;
		} 

		System.out.println("\n========Feed '" + feedItem.getTitle() + "' was accepted by filter");
		ILinkFormat linkStrategy = linkFormatManager.getLinkFormatFor(feedItem);
		if (linkStrategy == null) {
			return;
		}
		
		if (linkStrategy.existsTemp()) {
			return;
		}
		
		linkStrategy.download();
		
		if (!linkStrategy.existsTemp()) {
			return;
		}

		if (history.exists(linkStrategy.getHash())) {
			System.out.println("Torrent hash was downloaded before, quit.");
			return;
		}
		
		linkStrategy.sendToTorrentEngine();
		history.add(linkStrategy.getHash());
		
	}
}

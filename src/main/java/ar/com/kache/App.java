package ar.com.kache;

import java.io.IOException;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.config.ConfigFeed;
import ar.com.kache.formats.LinkFormatManager;
import ar.com.kache.history.FileHistory;
import ar.com.kache.history.IHistory;
import ar.com.kache.torrent.TorrentFeedCollector;

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
	
	public void process(String url) {
		ConfigFeed configFeed = new ConfigFeed();
		configFeed.setTitle("TestFeed");
		configFeed.setUrl(url);
		
		System.out.println("Feed " + configFeed.getUrl());
		
		try {
			process(configFeed);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void process(ConfigFeed configFeed) throws IOException {

		TorrentFeedCollector torrentFeedCollector = new TorrentFeedCollector(configFeed, history, linkFormatManager);
		try {
			torrentFeedCollector.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

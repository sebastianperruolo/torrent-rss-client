package ar.com.kache.config;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration {
	
	private String watchDir;
	private List<ConfigFeed> configFeeds;

	public List<ConfigFeed> getConfigFeeds() {
		return configFeeds;
	}

	public void setConfigFeeds(List<ConfigFeed> configFeeds) {
		this.configFeeds = configFeeds;
	}

	public String getWatchDir() {
		return watchDir;
	}

	public void setWatchDir(String watchDir) {
		this.watchDir = watchDir;
	}

}

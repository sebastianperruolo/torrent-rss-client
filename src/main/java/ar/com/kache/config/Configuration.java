package ar.com.kache.config;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {
	
	private String watchDir;
	private String magnetLinkCommand;
	
	@XmlElementWrapper(name="feeds")
	@XmlElement(name="feed")
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

	public String getMagnetLinkCommand() {
		return magnetLinkCommand;
	}

	/**
	 * @param magnetLinkCommand the magnetLinkCommand to set
	 */
	public void setMagnetLinkCommand(String magnetLinkCommand) {
		this.magnetLinkCommand = magnetLinkCommand;
	}

}

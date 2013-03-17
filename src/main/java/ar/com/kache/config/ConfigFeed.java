package ar.com.kache.config;

import it.sauronsoftware.feed4j.bean.FeedItem;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfigFeed {
	private String title;
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean accept(FeedItem feed) {
		//this must support some complicated algorithm
		//until I realize how to do it, accept everything
		return true;
	}
	
}

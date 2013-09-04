package ar.com.kache.formats;

import java.io.File;
import java.net.URL;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.utils.FileUtils;

public class TorrentFileLinkFormat implements ILinkFormat {
	private AppConfiguration appConfiguration;
	private File tempTorrent;
	private URL url;
	private String hash;
	
	public TorrentFileLinkFormat(AppConfiguration appConfiguration, URL url, String title) {
		String fileName = title + ".torrent";
		tempTorrent = new File(AppConfiguration.appTemp, fileName);
		this.url = url;
		this.appConfiguration = appConfiguration;
	}
	@Override
	public boolean existsTemp() {
		if (tempTorrent.isFile()) {
			System.out.println("Torrent file '" + tempTorrent.getAbsolutePath() + "' already exists");
			return true;
		}
		return false;
	}
	@Override
	public void download() {
		try {
			int code = FileUtils.getResponseCode(url);
			if (code != 200) {
				System.out.println("URL " + url.toString() + " return " + code + " code.");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			//return;
		}
		
		try {
			FileUtils.download(url, tempTorrent);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	@Override
	public String getHash() {
		if (hash == null) {
			hash = FileUtils.hash(tempTorrent);
		}
		return hash;
	}
	@Override
	public void sendToTorrentEngine() {
		File watchdirTorrent = new File(appConfiguration.getConfiguration().getWatchDir(), tempTorrent.getName());
		try {
			FileUtils.copyFile(tempTorrent, watchdirTorrent);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
	}

}

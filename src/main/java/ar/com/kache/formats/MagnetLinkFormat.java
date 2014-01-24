package ar.com.kache.formats;

import java.io.File;
import java.io.PrintWriter;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.utils.FileUtils;

public class MagnetLinkFormat implements ILinkFormat {
	private AppConfiguration appConfiguration;
	private File tempFile;
	private String url;
	private String hash;
	
	public MagnetLinkFormat(AppConfiguration appConfiguration, String url, String title) {
		String fileName = title + ".magnetlink";
		tempFile = new File(AppConfiguration.appTemp, fileName);
		this.url = url;
		this.appConfiguration = appConfiguration;
	}

	@Override
	public boolean existsTemp() {
		if (tempFile.isFile()) {
			System.out.println("Magnetlink file '" + tempFile.getAbsolutePath() + "' exists");
			return true;
		}
		System.out.println("Torrent file '" + tempFile.getAbsolutePath() + "' don't exists");
		return false;
	}
	@Override
	public void download() {
		System.out.println("Creating magnetlink file...");
		PrintWriter out = null;
		try {
			out = new PrintWriter(tempFile);
			out.println(url);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {out.close();} catch (Exception e) {}
			}
		}
	}

	@Override
	public String getHash() {
		if (hash == null) {
			hash = FileUtils.hash(url);
		}
		return hash;
	}

	@Override
	public void sendToTorrentEngine() {
		appConfiguration.getConfiguration().sendMagnetLink(url);
	}

}

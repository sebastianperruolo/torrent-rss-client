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
			System.out.println("Torrent file '" + tempFile.getAbsolutePath() + "' already exists");
			return true;
		}
		return false;
	}
	@Override
	public void download() {
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
		//"/usr/bin/transmission-remote --add \"%s\"";
		String soCommand = appConfiguration.getConfiguration().getMagnetLinkCommand();
		
		String command = String.format(soCommand, url);
		try {
			System.out.println("Executing command: " + command);
			Process tr = Runtime.getRuntime().exec( command );
			tr.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}

package ar.com.kache.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class FileUtils {
	
	private FileUtils() {
		
	}
	
	public static int getResponseCode(URL url) throws MalformedURLException, IOException {
	    HttpURLConnection huc =  (HttpURLConnection)  url.openConnection(); 
	    huc.setRequestMethod("GET"); 
	    huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
	    huc.connect();
	    return huc.getResponseCode();
	}

	public static void download(URL url, File target) throws IOException {
		System.out.println("Downloading " + url.toString() + "\n\tto "
				+ target.getAbsolutePath());
		InputStream stream = null;
		FileOutputStream fout = null;

		try {
			URLConnection connection = url.openConnection();
			stream = connection.getInputStream();
			if ("gzip".equals(connection.getContentEncoding())) {
				stream = new GZIPInputStream(stream);
			}

			fout = new FileOutputStream(target);

			byte data[] = new byte[1024];
			int count;
			while ((count = stream.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (stream != null) {
				stream.close();
			}
			if (fout != null) {
				fout.close();
			}
		}

	}
	
	public static String hash(String magnetLink) {
		Pattern pattern = Pattern.compile("(?<=\\bbtih\\b).*?(?=\\bdn=\\b)");
		Matcher matcher = pattern.matcher(magnetLink);
		if (matcher.find()) {
			return magnetLink.substring(matcher.start() + 1, matcher.end()-1);
		}
		return null;
	}
	public static String hash(File torrent) {
		byte[] bytes = torrentHash(torrent);
		if (bytes == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}

	private static byte[] torrentHash(File file) {
		InputStream input = null;

		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			input = new FileInputStream(file);
			StringBuilder builder = new StringBuilder();
			boolean eof = false;
			while (!builder.toString().endsWith("4:info") && !eof) {
				int r = input.read();
				builder.append((char)r); // It's ASCII anyway.
				eof = (r == -1);
			}
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			for (int data; (data = input.read()) > -1; output.write(data)) {
				//no op
			}
			sha1.update(output.toByteArray(), 0, output.size() - 1);
			return sha1.digest(); // Here's your hash. Do your thing with it.
		} catch (Exception e) {
			System.out.println("Failed to get hash from file "
					+ file.getAbsolutePath());
			e.printStackTrace();
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ignore) {
				}
			}
		}
	}

	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

}

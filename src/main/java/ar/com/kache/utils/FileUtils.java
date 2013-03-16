package ar.com.kache.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {
	private FileUtils() {
		
	}
	
	public static void download(URL url, File target) throws IOException {
		System.out.println("Downloading " + url.toString() + " to " + target.getAbsolutePath());
		BufferedInputStream in = null;
    	FileOutputStream fout = null;
    	try {
    		in = new BufferedInputStream(url.openStream());
    		fout = new FileOutputStream(target);

    		byte data[] = new byte[1024];
    		int count;
    		while ((count = in.read(data, 0, 1024)) != -1) {
    			fout.write(data, 0, count);
    		}
    	} catch (IOException e) {
    		throw e;
    	} finally {
    		if (in != null) {
    			in.close();
    		}
    		if (fout != null) {
    			fout.close();
    		}
    	}

	}

	public static String hash(File torrent) throws IOException, NoSuchAlgorithmException {
		byte[] bytes = torrentHash(torrent);
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
          sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
	}
	
	private static byte[] torrentHash(File file) throws IOException, NoSuchAlgorithmException {
		MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
		InputStream input = null;

		try {
		    input = new FileInputStream(file);
		    StringBuilder builder = new StringBuilder();
		    while (!builder.toString().endsWith("4:info")) {
		        builder.append((char) input.read()); // It's ASCII anyway.
		    }
		    ByteArrayOutputStream output = new ByteArrayOutputStream();
		    for (int data; (data = input.read()) > -1; output.write(data));
		    sha1.update(output.toByteArray(), 0, output.size() - 1);
		} finally {
		    if (input != null) try { input.close(); } catch (IOException ignore) {}
		}

		return sha1.digest(); // Here's your hash. Do your thing with it.
	}

}
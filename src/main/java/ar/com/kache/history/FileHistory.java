package ar.com.kache.history;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHistory implements IHistory {
	private File history;
	public FileHistory(File history) {
		this.history = history;
	}

	@Override
	public boolean exists(String hash) {
		if (!history.isFile()) {
			return false;
		}
		if (hash == null) {
			return false;
		}
		Scanner scanner;
		try {
			scanner = new Scanner(history);
		} catch (FileNotFoundException e) {
			return false;
		}
		String foundString = scanner.findWithinHorizon(hash, 0);
		
		return (foundString != null) && (!foundString.equals(""));
	}

	@Override
	public void add(String hash) {
		BufferedWriter bw = null;

		try {
		    bw = new BufferedWriter(new FileWriter(history, true));
		    bw.write(hash);
		    bw.newLine();
		    bw.flush();
		} catch (IOException ioe) {
		    ioe.printStackTrace();
		} finally { // always close the file
		    if (bw != null) {
		        try {
		            bw.close();
		        } catch (IOException ioe2) {
		            // just ignore it
		        }
		    }
		}
		System.out.println("Added " + hash);
	}

}

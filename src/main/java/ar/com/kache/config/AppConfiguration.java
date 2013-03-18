package ar.com.kache.config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller;

public class AppConfiguration {
	private File historyFile;
	private Configuration configuration = null;
	
	public static File userHome = new File(System.getProperty("user.home"));
	public static File appHome = new File(userHome, ".torrentrss");
	public static File appTemp = new File(appHome, "temp");
	public static File configFile = new File(appHome, "config.xml");
	
	private static final String NOW = new SimpleDateFormat("yyyy-MM-dd_HH-mm").format(new Date());
	public static File appLog = new File(appHome, "log/"+NOW);
	
	public AppConfiguration() {
		if (!userHome.isDirectory()) {
			throw new ConfigurationException("Error getting user home directory:" + userHome.getAbsolutePath());
		}
		
		if (!appHome.isDirectory()) {
			throw new ConfigurationException("Error getting app home directory:" + appHome.getAbsolutePath());
		}
		
		if (!configFile.isFile()) {
			throw new ConfigurationException("Config file '" + configFile.getAbsolutePath() + "' does not exists");
		}
		historyFile = new File(appHome, "history.txt");

		try {
        	JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            configuration = (Configuration)jaxbUnmarshaller.unmarshal(configFile);
        } catch (JAXBException e) {
        	throw new ConfigurationException("Error loading config file " + configFile.getAbsolutePath(), e);
        }
		
		appTemp.mkdirs();
		appLog.mkdirs();
	}
	
	public static void saveConfiguration(Configuration c, File file) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(c, file);
        } catch (JAXBException e) {
        	throw new ConfigurationException("Error save config file " + configFile.getAbsolutePath(), e);
        }
	}
	
	public File getHistoryFile() {
		return historyFile;
	}

	public Configuration getConfiguration() {
		return configuration;
	}
	
}

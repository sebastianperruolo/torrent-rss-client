package ar.com.kache;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.com.kache.config.AppConfiguration;
import ar.com.kache.config.ConfigFeed;
import ar.com.kache.config.Configuration;
import ar.com.kache.filters.BlackListFilterStrategy;
import ar.com.kache.filters.RegExFilterStrategy;
import ar.com.kache.filters.WhiteListFilterStrategy;
import ar.com.kache.utils.MyOutput;


public class Main {
	
	private static void install() {
		if (!AppConfiguration.appHome.isDirectory()) {
			AppConfiguration.appHome.mkdirs();
		}
		if (AppConfiguration.configFile.isFile()) {
			System.out.println("Configuration file already exists");
			System.exit(-1);
		}
			
		Configuration example = new Configuration();
		File watchDir = new File(AppConfiguration.appHome, "your_torrent_watchfolder");
		watchDir.mkdirs();
		example.setWatchDir(watchDir.getAbsolutePath());
		example.setMagnetLinkCommand("/usr/bin/transmission-remote --add \"%s\"");
		List<ConfigFeed> configFeeds = new ArrayList<ConfigFeed>();
		
		ConfigFeed configFeed1 = new ConfigFeed();
		configFeed1.setTitle("YIFY 720p +8");
		configFeed1.setUrl("http://yify-torrents.com/rss/0/720p/All/8");
		//just a blacklist example
		configFeed1.setFilterStrategy(BlackListFilterStrategy.CODE);
		configFeed1.setFilters(Arrays.asList("Star Trek")); //no Star Trek please
		configFeeds.add(configFeed1);
		
		ConfigFeed configFeed2 = new ConfigFeed();
		configFeed2.setTitle("KickAssTorrent TV Shows");
		configFeed2.setUrl("http://kat.ph/tv/?rss=1");
		//just a regex example
		configFeed2.setFilterStrategy(RegExFilterStrategy.CODE);
		configFeed2.setFilters(Arrays.asList("(.+)S01E01(.+)HDTV(.+)")); //any new show in HD
		configFeeds.add(configFeed2);
		
		ConfigFeed configFeed3 = new ConfigFeed();
		configFeed3.setTitle("aRGENTeaM");
		configFeed3
				.setUrl("http://www.argenteam.net/rss/tvseries_torrents_hr.xml");
		// just a whitelist example
		configFeed3.setFilterStrategy(WhiteListFilterStrategy.CODE);
		configFeed3.setFilters(Arrays.asList("BoardwalkEmpire", "GameOfThrones", "HouseOfCards", "HowIMetYourMother", "MadMen", "BigBangTheory"));
		configFeeds.add(configFeed3);
		
		ConfigFeed configFeed4 = new ConfigFeed();
		configFeed4.setTitle("TestMagnetLink");
		configFeed4.setUrl("http://dl.dropboxusercontent.com/u/6278479/testMagnetLink.rss");
		configFeeds.add(configFeed4);
		
		example.setConfigFeeds(configFeeds);
		
		AppConfiguration.saveConfiguration(example, AppConfiguration.configFile);
		System.out.println("Created example configuration file :D");
		System.out.println("Torrents watch-folder: " + watchDir.getAbsolutePath());
		System.out.println("Customize it, edit " + AppConfiguration.configFile.getAbsolutePath());
		System.exit(0);
	}
	
    /**
     * @param args
     */
    public static void main(String[] args) {
    	
    	if (args.length > 0) {
    		if ("install".equals(args[0])) {
    			install();
    		}
    	}
    	
    	AppConfiguration appConfiguration = new AppConfiguration();
    	try {
    		File outputFile = new File(AppConfiguration.appLog, "output.log");
    		MyOutput output = new MyOutput(System.out, new PrintStream(outputFile));
    		System.setOut(output);
    		System.setErr(output);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	File watchDir = new File(appConfiguration.getConfiguration().getWatchDir());
    	if (!watchDir.isDirectory()) {
    		System.err.println("Watch folder does not exists '" + watchDir.getAbsolutePath() + "'");
    		System.exit(-1);
    	}
    	
    	App app = new App(appConfiguration);
    	app.execute();
    }
    
}
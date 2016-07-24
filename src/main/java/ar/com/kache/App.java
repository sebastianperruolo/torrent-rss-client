package ar.com.kache;

import ar.com.kache.collectors.ListCollector;
import ar.com.kache.collectors.TorrentCollector;
import ar.com.kache.filters.PatternFeedItemFilter;
import ar.com.kache.handlers.FeedATHandler;
import ar.com.kache.model.FeedItem;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

    private static Map<String, List<String>> process(String[] args) {
        final Map<String, List<String>> params = new HashMap<>();

        List<String> options = null;
        for (int i = 0; i < args.length; i++) {
            final String a = args[i];

            if (a.charAt(0) == '-') {
                if (a.length() < 2) {
                    System.err.println("Error at argument " + a);
                    return params;
                }

                options = new ArrayList<>();
                params.put(a.substring(1), options);
            }
            else if (options != null) {
                options.add(a);
            }
            else {
                System.err.println("Illegal parameter usage");
                return params;
            }
        }
        return params;
    }

    private static String get(String key, Map<String, List<String>> params) {
        if (params.containsKey(key)) {
            if (!params.get(key).isEmpty()) {
                return params.get(key).get(0);
            }
        }
        System.err.print(String.format("Missing parameter \"%s\"", key));
        System.exit(1);
        return null;
    }

    public static void main(String[] args) {
        Map<String, List<String>> params = process(args);
        String url = get("url", params);
        String include = get("include", params);

        FeedATHandler handler = new FeedATHandler(new TorrentCollector(new PatternFeedItemFilter(include)));

        try {
            System.out.println("Processing: " + url);
            FeedSAXParser.parse(new URL(url).openStream(), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

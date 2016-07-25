package ar.com.kache;

import ar.com.kache.collectors.TorrentCollector;
import ar.com.kache.filters.PatternFeedItemFilter;
import ar.com.kache.handlers.FeedATHandler;
import ar.com.kache.utils.URLUtils;

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

    private static String mandatory(String key, Map<String, List<String>> params) {
        if (params.containsKey(key)) {
            if (!params.get(key).isEmpty()) {
                return params.get(key).get(0);
            }
        }
        System.err.print(String.format("Missing parameter \"%s\"", key));
        System.exit(1);
        return null;
    }

    private static String optional(String key, Map<String, List<String>> params) {
        if (params.containsKey(key)) {
            if (!params.get(key).isEmpty()) {
                return params.get(key).get(0);
            } else {
                return "";
            }
        }
        //System.out.println("containsKey " + key +  params.containsKey(key));
        //System.out.println("isEmpty " + key +  params.get(key).isEmpty());
        return null;
    }

    private static String bypass(String key, Map<String, List<String>> params) {
        String bypass = optional(key, params);
        if (bypass == null) {
            return "";
        }
        return " -" + key + " " + bypass;
    }

    public static void main(String[] args) {
        Map<String, List<String>> params = process(args);
        // System.out.println(params.toString());

        String url = mandatory("url", params);
        String include = mandatory("include", params);
        String bypass = bypass("-download-dir", params)
                + bypass("-start-paused", params);

        //System.out.println("bypass: " + bypass);

        FeedATHandler handler = new FeedATHandler(new TorrentCollector(new PatternFeedItemFilter(include), bypass));

        try {
            FeedSAXParser.parse(URLUtils.get(url), handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

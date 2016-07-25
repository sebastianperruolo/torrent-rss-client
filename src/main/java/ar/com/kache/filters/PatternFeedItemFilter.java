package ar.com.kache.filters;

import ar.com.kache.model.FeedItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sperruolo on 7/24/16.
 */
public class PatternFeedItemFilter implements FeedItemFilter {
    private final List<String> items;

    public PatternFeedItemFilter(String value) {
        this.items = Arrays.asList(value.split("\\s*,\\s*"));
    }

    @Override
    public boolean valid(FeedItem elem) {
        if (elem.title == null) {
            return false;
        }
        for (String name : items) {
            if (elem.title.contains(name)) {
                return true;
            }
        }
        return false;
    }
}

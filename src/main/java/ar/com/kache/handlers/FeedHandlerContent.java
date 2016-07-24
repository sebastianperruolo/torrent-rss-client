package ar.com.kache.handlers;

/**
 * Created by sperruolo on 7/24/16.
 */
public class FeedHandlerContent {
    private StringBuilder content = new StringBuilder();
    public void append(String text) {
        content.append(text);
    }

    public void start() {
        content = new StringBuilder();
    }

    public String text() {
        return content.toString();
    }
}

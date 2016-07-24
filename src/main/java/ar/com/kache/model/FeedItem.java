package ar.com.kache.model;

/**
 * Created by sperruolo on 7/24/16.
 */
public class FeedItem {
    public final String title;
    public final String link;
    public final String description;
    private final String pubDate;

    public FeedItem(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }

    public static class FeedItemBuilder {
        private String title;
        private String link;
        private String description;
        private String pubDate;

        public FeedItem build() {
            return new FeedItem(title, link, description, pubDate);
        }

        public void title(String content) {
            this.title = content;
        }

        public void link(String content) {
            this.link = content;
        }

        public void description(String content) {
            this.description = content;
        }

        public void pubDate(String content) {
            this.pubDate = content;
        }
    }
}

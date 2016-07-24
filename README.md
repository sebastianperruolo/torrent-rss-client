# torrent-rss-client

A Transmission complement for torrent rss.

## Version 2

In this new version I started from scratch trying to be faster and simpler.

Some of the changes in this version:

1. Avoid using external libraries as much as possible.
2. Use SAX instead of DOM to parse the feed ([sample][1]).

## First release

The first release only supports one type of feed so if you get a parsing error, please create a ticket with all the information you have.

## Download and run

To download this app:

```sh
wget https://github.com/sebastianperruolo/torrent-rss-client/releases/download/v2_0_0/torrent-rss-client-2.0-SNAPSHOT.jar

```

To run this app:

```sh
java -cp torrent-rss-client-2.0-SNAPSHOT.jar ar.com.kache.App -url [Feed URL] -include [Start Text]
```

For example, in order to processing [this feed][2] and then filter the results to keep any ubuntu ISO:

```sh
java -cp torrent-rss-client-2.0-SNAPSHOT.jar ar.com.kache.App -url https://raw.githubusercontent.com/sebastianperruolo/torrent-rss-client/master/src/test/resources/feeds/atfeed.xml -include ubuntu
```

That same line can be added as a cron task.


[1]: https://www.javacodegeeks.com/2013/05/parsing-xml-using-dom-sax-and-stax-parser-in-java.html
[2]: https://raw.githubusercontent.com/sebastianperruolo/torrent-rss-client/master/src/test/resources/feeds/atfeed.xml
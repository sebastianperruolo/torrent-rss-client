package ar.com.kache.formats;

public interface ILinkFormat {

	boolean existsTemp();

	void download();

	String getHash();

	void sendToTorrentEngine();

}

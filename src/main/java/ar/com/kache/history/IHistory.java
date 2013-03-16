package ar.com.kache.history;

public interface IHistory {

	boolean exists(String hash);

	void add(String hash);

}

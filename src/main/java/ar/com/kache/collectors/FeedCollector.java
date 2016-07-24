package ar.com.kache.collectors;

import ar.com.kache.model.Employee;

/**
 * Created by sperruolo on 7/24/16.
 */
public interface FeedCollector<E> {
    void add(E elem);
}

package ar.com.kache.collectors;

import ar.com.kache.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sperruolo on 7/24/16.
 */
public class ListCollector<E> implements FeedCollector<E> {

    private List<E> list = new ArrayList<>();

    @Override
    public void add(E e) {
        list.add(e);
    }

    public List<E> getList() {
        return list;
    }
}

package org.dp.core.strategy;

public class DefaultComparator<T> implements Comparator<T>{

    public static final DefaultComparator comparator = new DefaultComparator();

    @Override
    public int compare(T o1, T o2) {
        return 0;
    }
}

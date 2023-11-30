package org.dp.core.strategy;

/*
FunctionalInterface 只允许有一个abstract，其他都要为default
 */
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);

    default void m(){
        System.out.println("m");
    }
}

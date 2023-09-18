package org.algorithm.graph;

import java.util.*;

public class UnionFindWithSet<T> {
    Map<T, Set<T>> nodeSetMap;

    public UnionFindWithSet(List<T> nodes) {
        nodeSetMap = new HashMap<>();
        for (T n : nodes) {
            HashSet<T> set = new HashSet<>();
            set.add(n);
            nodeSetMap.put(n, set);
        }
    }

    public boolean isSameSet(T v1, T v2) {
        return nodeSetMap.get(v1) == nodeSetMap.get(v2);
    }

    public void union(T v1, T v2) {
        if (isSameSet(v1, v2)) return;
        Set<T> s1 = nodeSetMap.get(v1);
        Set<T> s2 = nodeSetMap.get(v2);
        s1.addAll(s2);
        nodeSetMap.put(v2, s1);
    }
}

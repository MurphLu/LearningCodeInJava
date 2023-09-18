package org.algorithm.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind<T> {
    Map<T, T> parent;
    Map<T, Integer> rank;

    public UnionFind(List<T> list) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (T t : list) {
            parent.put(t, t);
            rank.put(t, 0);
        }
    }

    public T find(T v) {
        // 如果当前节点不为根，那么继续向上查找，并压缩路径
        if (parent.get(v) != v) {
            parent.put(v, find(parent.get(v)));
        }
        return parent.get(v);
    }

    public boolean isSameRoot(T v1, T v2) {
        return find(v1) == find(v2);
    }

    public void union(T v1, T v2) {
        T root1 = find(v1);
        T root2 = find(v2);
        if (root1 != root2) {
            if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }
}

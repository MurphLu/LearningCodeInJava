package org.algorithm.leetcode.daily;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class SmallestInfiniteSet {
    PriorityQueue<Integer> queue;
    Set<Integer> set;
    public SmallestInfiniteSet() {
        this.queue = new PriorityQueue<>();
        set = new HashSet<>();
    }

    public int popSmallest() {
        int a = queue.poll();
        set.remove(a);
        return a;
    }

    public void addBack(int num) {
        if (set.contains(num)) {
            return;
        }
        set.add(num);
        queue.add(num);
    }
}

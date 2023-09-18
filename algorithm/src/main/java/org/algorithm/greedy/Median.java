package org.algorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Median {
    public static void main(String[] args) {
        System.out.println(calculateMedian(new int[]{1,2,3,4,5}));
    }
    public static int calculateMedian(int[] values) {
        if (values == null || values.length == 0) { return 0;}
        PriorityQueue<Integer> minRoot = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        PriorityQueue<Integer> maxRoot = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        maxRoot.add(values[0]);
        for (int i = 1; i < values.length; i++) {
            int cur = values[i];
            if (maxRoot.peek() < cur) {
                minRoot.add(cur);
            } else {
                maxRoot.add(cur);
            }
            while (Math.abs(maxRoot.size() - minRoot.size()) > 1) {
                if (maxRoot.size() > minRoot.size()) {
                    minRoot.add(maxRoot.poll());
                } else {
                    maxRoot.add(minRoot.poll());
                }
            }
        }
        if (maxRoot.size() == minRoot.size()) {
            return (maxRoot.peek() + minRoot.peek()) / 2;
        } else {
            return maxRoot.size() < minRoot.size() ? minRoot.peek() : maxRoot.peek();
        }
    }
}

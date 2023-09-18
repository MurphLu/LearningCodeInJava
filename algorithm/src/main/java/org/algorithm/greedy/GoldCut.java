package org.algorithm.greedy;

import java.util.PriorityQueue;

public class GoldCut {
    public static void main(String[] args) {

    }

    public static int minCost(int[] values) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int value : values) {
            pq.add(value);
        }

        int sum = 0;
        int cur;
        while (pq.size() > 1) {
            cur = pq.poll() + pq.poll();
            pq.add(cur);
            sum += cur;
        }
        return sum;
    }
}

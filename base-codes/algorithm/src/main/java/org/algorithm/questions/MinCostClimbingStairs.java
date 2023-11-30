package org.algorithm.questions;

import java.util.Arrays;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] a = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(new MinCostClimbingStairs().minCost(a));
    }

    public int minCost(int[] cost) {
        int min = 0;
        for (int i = 2; i < cost.length ; i++) {
            min = Math.min(cost[i - 1], cost[i-2]);
            cost[i] = min + cost[i];
        }
        System.out.println(Arrays.toString(cost));
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}

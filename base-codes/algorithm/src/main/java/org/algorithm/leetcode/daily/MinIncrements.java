package org.algorithm.leetcode.daily;

import java.util.Arrays;

public class MinIncrements {

    static class Tree {
        int val;
        int maxSub;

    }
    public static void main(String[] args) {
        System.out.println(new MinIncrements().minIncrements(7, new int[]{1,5,2,2,3,3,1}));
    }

    public int minIncrements(int n, int[] cost) {
        int[] maxToCur = new int[cost.length];
        int maxCost = maxCost(0, cost, maxToCur);
        int target = maxCost - cost[0];
        int left = calc(1, target, cost, maxToCur);
        int right = calc(2, target, cost, maxToCur);

        System.out.println(maxCost);
        System.out.println(Arrays.toString(maxToCur));
        return left + right;
    }

    private int calc(int n, int target, int[] cost, int[] maxCostsToCur) {
        if (n >= cost.length) {
            return 0;
        }
        int increase = target - maxCostsToCur[n];
        int nextTarget = target - increase - cost[n];
        int left = calc(2*n + 1, nextTarget, cost, maxCostsToCur);
        int right = calc(2*n + 2, nextTarget, cost, maxCostsToCur);
        return increase + left + right;
    }

    private int maxCost(int n, int[] cost, int[] maxCostsToCur) {
        if (n >= cost.length) {
            return 0;
        }
        maxCostsToCur[n] = Math.max(maxCost(2*n+1, cost, maxCostsToCur), maxCost(2*n + 2, cost, maxCostsToCur)) + cost[n];
        return maxCostsToCur[n];
    }
}

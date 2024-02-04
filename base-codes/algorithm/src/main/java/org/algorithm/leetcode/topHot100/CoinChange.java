package org.algorithm.leetcode.topHot100;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(new CoinChange().coinChange(new int[]{2,1,3,4}, 10));
    }
    public int coinChange(int[] coins, int amount) {
        int n = coins.length - 1;
        Arrays.sort(coins);

        int[][] dp = new int[amount + 1][n+1];
        for (int[] arr:dp) {
            Arrays.fill(arr, -2);
        }
        return process(coins, n, amount, dp);
    }

    private int process(int[] coins, int currentCoinIdx, int amount, int[][] dp) {
        if (currentCoinIdx < 0) {
            return -1;
        }
        int coin = coins[currentCoinIdx];
        if (amount % coin == 0) {
            return amount / coin;
        }
        int maxCount = amount > coin ? amount / coin : 0;
        int min = Integer.MAX_VALUE;
        if (dp[amount][currentCoinIdx] != -2) {
            return dp[amount][currentCoinIdx];
        }
        for (int i = maxCount; i >=0 ; i--) {
            int fullCount = process(coins, currentCoinIdx - 1, amount - coin * i, dp);
            min = Math.min(fullCount == -1 ? Integer.MAX_VALUE : i + fullCount, min);
        }
        min = min == Integer.MAX_VALUE ? -1 : min;
        dp[amount][currentCoinIdx] = min;
        return min;
    }
}

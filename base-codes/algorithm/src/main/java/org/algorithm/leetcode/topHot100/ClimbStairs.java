package org.algorithm.leetcode.topHot100;

import java.util.Arrays;

// 每次一到两级台阶，有 n 级台阶一共有多少种方法
public class ClimbStairs {
    public static void main(String[] args) {
        new ClimbStairs().climbStairs(3);
    }
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return process(n, dp);
    }
    private int process(int n, int[] dp) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int res = 0;
        res += process(n - 1, dp);
        res += process(n - 2, dp);
        dp[n] = res;
        return res;
    }
}

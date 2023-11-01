package org.algorithm.leetcode.daily;

import java.util.Arrays;

/**
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 *
 * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
 *
 * 答案可能很大，你需要对 109 + 7 取模 。
 */
public class NumRollsToTarget {
    public static void main(String[] args) {
        System.out.println(dp(3, 5, 12));
//        System.out.println(recursion(30, 30, 500));
        System.out.println(dpFromRecord(30, 30, 500));
    }

    public static int recursion(int n, int k, int target) {
        return process(n, k, target);
    }

    public static int process(int n, int k, int target) {
        int mod = 1000000007;
        if (target < 0) {
            return 0;
        }
        if (n == 0) {
            if (target == 0){
                return 1;
            }else {
                return 0;
            }
        }
        int count = 0;
        for (int i = 1; i <=k ; i++) {
            count += process(n - 1, k, target - i);
            count %= mod;
        }
        return count;
    }

    public static int recordTable(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return processRecord(dp, n, k, target);
    }

    private static int processRecord(int[][] dp, int n, int k, int target) {
        int mod = 1000000007;
        if (target < 0) {
            return 0;
        }
        if (n == 0) {
            if (target == 0){
                return 1;
            }else {
                return 0;
            }
        }
        if (dp[n][target] != -1) {
            return dp[n][target];
        }
        int count = 0;
        for (int i = 1; i <=k ; i++) {
            count += processRecord(dp,n - 1, k, target - i);
            count %= mod;
        }
        dp[n][target] = count;
        return count;
    }

    public static int dpFromRecord(int n, int k, int target) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][target + 1];
        // 边界条件，当 n = 0，target = 0 时 return 1
        dp[0][0] = 1;
        for (int round = 1; round <= n; round++) {
            for (int targetBefore = round - 1; targetBefore <= Math.min(k * (round - 1), target); targetBefore++) {
                for (int curVal = 1; curVal <= k ; curVal++) {
                    int currTarget = targetBefore + curVal;
                    if (currTarget <= target){
                        dp[round][currTarget] += dp[round-1][targetBefore];
                        dp[round][currTarget] %= mod;
                    }
                }
            }
        }
        for (int[] arr: dp
             ) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[n][target];
    }

    public static int dp(int n, int k, int target) {
        int mod = 1000000007;
        int times = n + 1;
        int targets = target + 1;
        int[][] dp = new int[times][targets];
        dp[0][0] = 1;
        for (int i = 1; i < times; i++) {
            for (int j = i; j < targets && j <= k * i; j++) {
                if (i == j) {dp[i][j] = 1; continue;}
                int cur = 0;
                for (int l = Math.max(j - k, 0); l < j; l++) {
                    cur += dp[i - 1][l];
                    cur = cur % mod;
                }
                dp[i][j] = cur;
            }
        }
        for (int[] arr: dp
             ) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[n][target];
    }
    // 5, 6, 12
    /**
     * 有以下二维数组，
     * 纵向为当前一共 roll 了多少次，
     * 横向为 roll n 次每种结果能 roll 出来多少种不同的情况
     * 我们逐行分析，
     * 当次数为 0 时，target 为 0 的情况有一种，剩下的都不可能出现
     * 当次数为 1 时，只 roll 一次，那么只能出现 1-6 6种结果
     * 当次数为 2 时，本次能 roll 出的值为 1-6，加上前一次的值能 roll 出 2 - 12
     *   如果第二次 roll 出的 target 为 2，那么前两次都为 1，dp[2][2] = 1;
     *     同理 第 n 次 roll 出的 target 为 n 的话，说明每次 roll 出的都为1，那么 dp[n][n] = 1
     *   如果第二次 roll 出的 target 为 3，那么当前值可能为 1-2，上一次 roll 出的也为 1-2，dp[2][3] = dp[1][1] + dp[1][2]
     *   如果第二次 roll 出的 target 为 4，那么当前值可能为 1-3，上一次 roll 出的也为 1-3，dp[2][3] = dp[1][1] + dp[1][2] + dp[1][3]
     *   .....
     *   如果第二次 roll 出的 target 为 x，那么当前值可能为 1～x-1，上一次 roll 出的也为 1～x-1，
     *      那么 dp[2][x] = dp[1][Math.max(x - 6, 0)] + dp[1][Math.max(x - 6, 0) + 1] + dp[1][Math.max(x - 6, 0) + 2] .... + dp[1][x - 1]
     * 当次数为第 n 次时，能 roll 出 n ~ 6n
     *  dp[n][n] = 1;
     *  x 为当前 roll 完之后的 target 在 n ~ 6n 之间，当前 roll 出的点数为 1～6，那么上一次 roll 的点数则为 x - (1~6)
     *  dp[n][x] = dp[n - 1][Math.max(x - 6, 0)] + dp[n - 1][Math.max(x - 6, 0) + 1] + dp[n - 1][Math.max(x - 6, 0) + 2] + ....+ dp[n-1][x - 1];
     *
     *     0  1  2  3  4  5  6  7  8  9  10 11 12
     *  0  1  0  0  0  0  0  0  0  0  0  0  0  0
     *  1  0  1  1  1  1  1  1  0  0  0  0  0  0
     *  2  0  0  1  2  3  4  5  6  5  4  3  2  1
     *  3  0  0  0  1  3  6  10 15 21 25 27 27 25
     *  4  0  0  0  0  1  4  10 20 35 56 80 104 125
     *  5  0  0  0  0  0  1  5  15 35 70 126 205 305
     *
     *
     *
     *
     */
}

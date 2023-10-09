package org.algorithm.recursion;

// 1~N 这么多位置，robot 当前在 a，要到 b 去，要求走 k 步时到 b，在一个非头尾的位置可以向前也可以向后走一步
// 问：一共有多少种走法

import java.util.Arrays;

public class RobotWalk {
    public static void main(String[] args) {
        int N = 5;
        int end = 3;
        int start = 2;
        int K = 5;
        System.out.println(walkWay1(N, end, K, start));
        System.out.println(walkWay2(N, end, K, start));
        System.out.println(walkWay3(N, end, K, start));
    }

    //暴力递归 时间复杂度 2^k
    public static int walkWay1(int N, int end, int rest, int cur) {
        return process(N, end, rest, cur);
    }

    // 暴力递归
    private static int process(int N, int end, int rest, int cur) {
        if (rest == 0) {
            return cur == end ? 1 : 0;
        }
        if (cur == 1) {
            return process(N, end, rest - 1, 2);
        }
        if (cur == N) {
            return process(N, end, rest - 1, N - 1);
        }
        return process(N, end, rest - 1, cur - 1) + process(N, end, rest - 1, cur + 1);
    }

    // 记忆化搜索 O(K*N)
    public static int walkWay2(int N, int end, int rest, int cur) {
        int[][] dp = new int[rest + 1][N + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return process2(N, end, rest, cur, dp);
    }
    private static int process2(int N, int end, int rest, int cur, int[][] dp) {
        // 重复路径直接返回
        if (dp[rest][cur]!= -1) {
            return dp[rest][cur];
        }

        if (rest == 0) {
            dp[rest][cur] = cur == end ? 1 : 0;
            return dp[rest][cur];
        }

        if (cur == 1) {
            dp[rest][cur] = process2(N, end, rest - 1, 2, dp);
        } else if (cur == N) {
            dp[rest][cur] = process2(N, end, rest - 1, N - 1, dp);

        } else {
            dp[rest][cur] = process2(N, end, rest - 1, cur - 1, dp) + process2(N, end, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }


    // 严格表结构的动态规划

    /**
     * N = 5, end = 4, K = 4,
     * start = 2 result = 4
     *
     *    0  1  2  3  4  5  cur
     * 0  X  0  0  0  1  0
     * 1  X  0  0  1  0  1
     * 2  X  0  1  0  2  0
     * 3  X  1  0  3  0  2
     * 4  X  0  4  0  5  0
     * rest
     *
     */
    public static int walkWay3(int N, int end, int K, int cur) {
        int[][] dp = new int[K + 1][N + 1];
        dp[0][4] = 1; // if (rest == 0)  dp[rest][cur] = cur == end ? 1 : 0;
        for (int i = 1; i < dp.length; i++) {
            int[] stepRes = dp[i];
            for (int j = 0; j < stepRes.length; j++) {
                if (j == 0) { continue; }
                if (j == 1) { // if (cur == 1)
                    dp[i][j] = dp[i - 1][j + 1]; // dp[rest][cur] = process2(N, end, rest - 1, 2, dp);
                } else if (j == N) { // if (cur == N)
                    dp[i][j] = dp[i - 1][j - 1]; // process2(N, end, rest - 1, N - 1, dp);
                } else {
                    dp[i][j] = dp[i - 1][j + 1] + dp[i - 1][j - 1]; // process2(N, end, rest - 1, cur - 1, dp) + process2(N, end, rest - 1, cur + 1, dp);
                }
            }
        }
        return dp[K][end];
    }
}

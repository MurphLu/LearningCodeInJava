package org.algorithm.questions;

import java.util.Arrays;

public class DifPath {
    public static void main(String[] args) {
        System.out.println(new DifPath().uniquePaths(3, 7));
        System.out.println(new DifPath().processDP(1, 1));
    }
    public int uniquePaths(int m, int n) {
        return process(m,n,0,0);
    }

    public int process(int m, int n, int x, int y) {
        if (x >= m || y >= n) {
            return 0;
        }
        if (x == m-1 && y == n-1) {
            return 1;
        }
        return process(m, n, x + 1, y) + process(m, n, x, y + 1);
    }

    public int processDP(int m, int n) {
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >=0 ; i--) {
            for (int j = n - 1; j >= 0 ; j--) {
                if (i == m - 1 && j == n - 1) { continue; }
                dp[i][j] = getValue(dp, i + 1, j) + getValue(dp, i, j + 1);
            }
        }
        for (int[] arr: dp
             ) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[0][0];
    }

    public int getValue(int[][] dp,  int x, int y) {
        if (x >= dp.length || y >= dp[0].length) {
            return 0;
        }

        if (x == dp.length -1 && y == dp[0].length-1) {
            return 1;
        }
        return dp[x][y];
    }
}

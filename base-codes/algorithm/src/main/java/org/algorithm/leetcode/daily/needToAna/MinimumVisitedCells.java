package org.algorithm.leetcode.daily.needToAna;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
 *
 * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
 *
 * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
 * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
 * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 */
public class MinimumVisitedCells {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cnt = new int[m][n];
        // 填充最大值，所有单元格数+1
        int max = m * n + 1;
        for(int[] arr : cnt) {
            Arrays.fill(arr, max);
        }
        // 起点格子
        cnt[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 从 00 开始，如果之后某一个格子值 == max 说明无法跳到该格子
                if (cnt[i][j] == max) continue;
                f(i,j,grid[i][j], cnt, grid);
            }
        }
        if (cnt[m - 1][n - 1] == max) {
            return -1;
        }
        return cnt[m - 1][n - 1] == max ? -1 : cnt[m - 1][n - 1];
    }

    private void f(int i, int j, int k, int[][] cnt, int[][] grid) {
        if (k == 0) return;
        // i < k <= k+i
        for (int x = 1; x <=k && (i+x)<cnt.length ; x++) {
            cnt[i+x][j] = Math.min(cnt[i][j]+1, cnt[i+x][j]);
            // 如果到达当前格子比起点格子的步数少或者相等，则从当前格子继续执行 for，但如果当前格子可以调出起点格子能到达的范围，
            // 那么直接跳出，上一层方法的 for 循环会遍历到该格子，并1️以该格子为起点继续遍历
            if (cnt[i+x][j] <= cnt[i][j]) {
                x+=grid[i+x][j];
                continue;
            }
        }

        for (int x = 1; x <=k && (j+x) < cnt[0].length; x++) {
            cnt[i][j+x] = Math.min(cnt[i][j] + 1, cnt[i][j+x]);
            if (cnt[i][j+x] <= cnt[i][j]) {
                x+= grid[i][j+x];
                continue;
            }
        }
    }

}

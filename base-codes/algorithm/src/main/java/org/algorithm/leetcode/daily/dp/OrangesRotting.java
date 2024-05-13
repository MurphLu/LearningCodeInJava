package org.algorithm.leetcode.daily.dp;

import java.util.*;

/**
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class OrangesRotting {
    public static void main(String[] args) {
        new OrangesRotting().orangesRotting(
                new int[][]{new int[]{0,2,2}}
        );
    }
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] record = new int[m][n];
        for(int[] arr: record) {
            Arrays.fill(arr, -1);
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                    record[i][j] = 0;
                }
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0];
            int j = cur[1];
            if(record[i][j] > 0 && record[i][j] != step) {
                step++;
            }
            List<int[]> ints = generateNextSteps(i, j, step, record, grid);
            queue.addAll(ints);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && record[i][j] == -1) {
                    return -1;
                }
            }
        }
        return step;
    }
    private List<int[]> generateNextSteps(int i, int j, int step, int[][] record, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> rec = new ArrayList<>();
        if (i > 0) {
            if (record[i-1][j] == -1 && grid[i-1][j] == 1) {
                rec.add(new int[]{i-1, j});
                record[i-1][j] = step+1;
            }
        }
        if (i < m-1) {
            if (record[i+1][j] == -1 && grid[i+1][j] == 1) {
                rec.add(new int[]{i+1, j});
                record[i+1][j] = step+1;
            }
        }
        if (j > 0) {
            if (record[i][j-1] == -1 && grid[i][j-1] == 1) {
                rec.add(new int[]{i, j-1});
                record[i][j-1] = step+1;
            }

        }
        if (j < n-1) {
            if(record[i][j+1] == -1 && grid[i][j+1] == 1){
                rec.add(new int[]{i, j+1});
                record[i][j+1] = step+1;
            }
        }
        return rec;
    }
}

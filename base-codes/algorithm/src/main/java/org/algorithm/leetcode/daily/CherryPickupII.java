package org.algorithm.leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个 rows x cols 的矩阵 grid 来表示一块樱桃地。 grid 中每个格子的数字表示你能获得的樱桃数目。
 *
 * 你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 (0,0) 出发，机器人 2 从右上角格子 (0, cols-1) 出发。
 *
 * 请你按照如下规则，返回两个机器人能收集的最多樱桃数目：
 *
 * 从格子 (i,j) 出发，机器人可以移动到格子 (i+1, j-1)，(i+1, j) 或者 (i+1, j+1) 。
 * 当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。
 * 当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。
 * 两个机器人在任意时刻都不能移动到 grid 外面。
 * 两个机器人最后都要到达 grid 最底下一行。
 */
public class CherryPickupII {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{1, 0, 0, 3},
                new int[]{0, 0, 0, 3},
                new int[]{0, 0, 3, 3},
                new int[]{9, 0, 3, 3}};
        grid = new int[][]{
                        new int[]{3,1,1},
                        new int[]{2,5,1},
                        new int[]{1,5,5},
                        new int[]{2,1,1}

        };
        int i =
                new CherryPickupII().cherryPickup(grid);
        System.out.println(i);
    }
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] memo = new int[m][n][n];
        for(int[][] dArr: memo) {
            for(int[] arr: dArr) {
                Arrays.fill(arr, -1);
            }
        }
        return dp(grid, 0, 0, n-1, memo);
    }

    /**
     *
     * @param grid 樱桃地
     * @param d 当前两个机器人向下走的步数，因为机器人每次只能向下走（左下，下，右下）所以每个时刻机器人所在的行都是相同的
     * @param rb1 机器人 1 所在的横坐标
     * @param rb2 机器人 2 所在的横坐标
     * @param memo 记录
     * @return 返回每种状态最大值
     */
    private int dp(int[][] grid, int d, int rb1, int rb2, int[][][] memo) {
        int m = grid.length;
        int n = grid[0].length;
        // 走出樱桃地则返回 0；
        if (d >= m || rb1 < 0 || rb2 < 0 || rb1 >= n || rb2 >= n) {
            return 0;
        }

        int max = 0;

        // 如果当前记录已经存在，则直接返回
        if (memo[d][rb1][rb2] != -1) {
            return memo[d][rb1][rb2];
        }
        // 当前两个机器人所在位置的樱桃数
        int valRb1 = grid[d][rb1];
        int valRb2 = grid[d][rb2];
        // 如果两个机器人在同一个位置，则只计算一次
        if (rb1 == rb2){
            valRb2 = 0;
        }

        // 两个机器人下一步能走的情况组合，获取能拿到的最大值
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                max = Math.max(dp(grid, d + 1, rb1 + i, rb2 + j, memo) + valRb1 + valRb2, max);
            }
        }
        // 记录状态
        memo[d][rb1][rb2] = max;
        // 返回
        return max;
    }
}

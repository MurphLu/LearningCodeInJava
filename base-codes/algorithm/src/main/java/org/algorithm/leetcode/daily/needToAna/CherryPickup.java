package org.algorithm.leetcode.daily.needToAna;


import java.util.Arrays;

/**
 * 给你一个 n x n 的网格 grid ，代表一块樱桃地，每个格子由以下三种数字的一种来表示：
 *
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 请你统计并返回：在遵守下列规则的情况下，能摘到的最多樱桃数：
 *
 * 从位置 (0, 0) 出发，最后到达 (n - 1, n - 1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为 0 或者 1 的格子）；
 * 当到达 (n - 1, n - 1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为 0 ）；
 * 如果在 (0, 0) 和 (n - 1, n - 1) 之间不存在一条可经过的路径，则无法摘到任何一个樱桃。
 */
public class CherryPickup {
    public static void main(String[] args) {
        // :[[0,1,1,0,0],
        //   [1,1,1,1,0],
        //   [-1,1,1,1,-1],
        //   [0,1,1,1,0],
        //   [1,0,-1,0,0]]
//        int[][] grid = new int[][]{
//                new int[]{0,1,-1},
//                new int[]{1,0,-1},
//                new int[]{1,1,1},
//        };
        // [[0,1,-1],
        //  [1,0,-1],
        //  [1,1,1]]
        int[][] grid =
//                new int[][]{
//                new int[]{ 0,1, 1,0, 0},
//                new int[]{ 1,1, 1,1, 0},
//                new int[]{-1,1, 1,1,-1},
//                new int[]{ 0,1, 1,1, 0},
//                new int[]{ 1,0,-1,0, 0}
//        };
                new int[][]{
                new int[]{1,1,0,0,0,0,0},
                new int[]{0,-1,0,1,0,0,0},
                new int[]{0,-1,-1,-1,0,0,1},
                new int[]{1,0,0,0,0,0,-1},
                new int[]{0,0,-1,1,-1,0,-1},
                new int[]{0,0,-1,1,-1,0,0},
                new int[]{0,0,0,1,1,1,-1}
        };
        new CherryPickup().cherryPickup(grid);
    }
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n * 2 - 1][n][n];
        for (int[][] m : memo) {
            for (int[] r : m) {
                Arrays.fill(r, -1); // -1 表示没有计算过
            }
        }
        int res1 = Math.max(dfs(n * 2 - 2, n - 1, n - 1, grid, memo), 0);
        int[][][] memo2 = new int[n * 2 - 1][n][n];
        for (int[][] m : memo2) {
            for (int[] r : m) {
                Arrays.fill(r, -1); // -1 表示没有计算过
            }
        }
        int res2 = Math.max(dp(grid, 0, 0, 0, memo2), 0);
        System.out.println(res1);
        System.out.println(res2);
        return 0;
    }
    private int dfs(int t, int j, int k, int[][] grid, int[][][] memo) {
        // 不能出界，不能访问 -1 格子
        if (j < 0 || k < 0 || t < j || t < k || grid[t - j][j] < 0 || grid[t - k][k] < 0) {
            return Integer.MIN_VALUE;
        }
        if (t == 0) {
            // 此时 j = k = 0
            return grid[0][0];
        }
        if (memo[t][j][k] != -1) { // 之前计算过
            return memo[t][j][k];
        }
        int res = Math.max(
                Math.max(
                        dfs(t - 1, j, k, grid, memo),
                        dfs(t - 1, j, k - 1, grid, memo)),
                Math.max(
                        dfs(t - 1, j - 1, k, grid, memo),
                        dfs(t - 1, j - 1, k - 1, grid, memo))
        ) + grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
        memo[t][j][k] = res; // 记忆化
        return res;
    }

    /**
     * 去+回 == 两次去，两个机器人从 0，0 同时出发，同时到达终点，所能摘得的最大樱桃数
     * @param grid 樱桃地
     * @param t 一共走的步数
     * @param go 第一次
     * @param back 第二次
     * @param memo 记录
     * @return 返回每个状态最大值
     */
    private int dp(int[][] grid, int t, int go, int back, int[][][] memo) {
        int n = grid.length;
        if (go >= n || back >= n || t-go >= n || t-back >= n || grid[t-go][go] == -1 || grid[t-back][back] == -1) {
            return Integer.MIN_VALUE;
        }
        if (memo[t][go][back] != -1) {
            return memo[t][go][back];
        }

        if (t == 2*n - 2) {
            return grid[n-1][n-1] == -1 ? Integer.MIN_VALUE : grid[n-1][n-1];
        }

        int max = Integer.MIN_VALUE;
        max = Math.max(dp(grid, t + 1, go, back, memo), max);
        max = Math.max(dp(grid, t + 1, go + 1, back, memo), max);
        max = Math.max(dp(grid, t + 1, go, back + 1, memo), max);
        max = Math.max(dp(grid, t + 1, go + 1, back + 1, memo), max);
        max += grid[t-go][go] + (go == back ? 0 : grid[t-back][back]);
        memo[t][go][back] = max;
        return max;
    }
}

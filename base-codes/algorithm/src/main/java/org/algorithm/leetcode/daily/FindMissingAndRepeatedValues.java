package org.algorithm.leetcode.daily;

/**
 * 给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n2] 范围内。除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
 *
 * 任务是找出重复的数字a 和缺失的数字 b 。
 *
 * 返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
 */
public class FindMissingAndRepeatedValues {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int[] nums = new int[n*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i][j];
                nums[num-1]++;
            }
        }
        int miss = 0;
        int repeat = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                miss = i + 1;
            }
            if (nums[i] == 2) {
                repeat = i + 1;
            }
        }
        return new int[]{repeat, miss};
    }
}

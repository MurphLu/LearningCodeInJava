package org.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 *
 * 题目数据保证答案符合 32 位整数范围。
 */
public class CombainationSum4 {
    public static void main(String[] args) {
        int i = new CombainationSum4().combinationSum4(
                new int[]{2,1,3}, 35
        );
        System.out.println(i);
    }
    public int combinationSum4(int[] nums, int target) {
        int[][] dp  = new int[nums.length][target+1];
        for(int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        int sum = 0;
        for (int num : nums) {
            sum += process(nums, target - num, dp);
        }

        return sum;
    }

    public int process(int[] nums, int target, int[][] dp) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i][target] == -1) {
                dp[i][target] = process(nums, target - nums[i], dp);
            }
            sum += dp[i][target] ;
        }
        return sum;
    }


}

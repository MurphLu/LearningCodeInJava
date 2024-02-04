package org.algorithm.leetcode.topHot100;

import java.util.Arrays;
// 打家劫舍，不能偷相邻两户
public class Rob {
    public static void main(String[] args) {
        System.out.println(new Rob().rob(new int[]{2,7,9,3,1}));
    }
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return process(nums, 0, dp);
    }

    private int process(int[] nums, int idx, int[] dp) {
        if (idx >= nums.length) return 0;
        if (dp[idx] != -1) {
            return dp[idx];
        }
        int res = 0;
        res =Math.max(nums[idx] + process(nums, idx + 2 , dp),
        process(nums, idx + 1, dp));
        dp[idx] = res;
        return res;
    }

}

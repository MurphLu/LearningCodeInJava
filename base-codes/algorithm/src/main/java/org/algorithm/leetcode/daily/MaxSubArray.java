package org.algorithm.leetcode.daily;

// 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
// 子数组 是数组中的一个连续部分。

public class MaxSubArray {
    public static void main(String[] args) {

    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = preMax < 0 ? nums[i] : preMax + nums[i];
            preMax = cur;
            max = Math.max(max, cur);
        }
        return max;
    }
}

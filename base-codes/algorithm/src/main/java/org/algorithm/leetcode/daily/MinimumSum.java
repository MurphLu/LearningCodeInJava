package org.algorithm.leetcode.daily;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。
 *
 * 如果下标三元组 (i, j, k) 满足下述全部条件，则认为它是一个 山形三元组 ：
 *
 * i < j < k
 * nums[i] < nums[j] 且 nums[k] < nums[j]
 * 请你找出 nums 中 元素和最小 的山形三元组，并返回其 元素和 。如果不存在满足条件的三元组，返回 -1 。
 *
 * 暴力解
 */

public class MinimumSum {
    public int minimumSum(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        int midIdx = 1;
        while (midIdx < nums.length - 1) {
            int midVal = nums[midIdx];
            Integer minLeft = null;
            for (int i = 0; i < midIdx; i++) {
                int leftVal = nums[i];
                if (leftVal < midVal) {
                    minLeft = minLeft == null ? leftVal : Math.min(minLeft, leftVal);
                }
            }
            Integer minRight = null;
            for (int i = midIdx + 1; i < nums.length; i++) {
                int rightVal = nums[i];
                if (rightVal < midVal) {
                    minRight = minRight == null ? rightVal : Math.min(minRight, rightVal);
                }
            }
            if (minLeft!=null && minRight!=null) {
                minSum = Math.min(minSum, minLeft + minRight+ midVal);
            }
            midIdx++;
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}

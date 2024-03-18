package org.algorithm.leetcode.daily;

import java.util.Arrays;

/**
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 *
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，
 * 包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 */
public class SumRange {
    public static void main(String[] args) {
        SumRange sumRange = new SumRange(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(Arrays.toString(sumRange.preSum));
        System.out.println(sumRange.sumRange(0, 2));
    }
    int[] nums;
    int[] preSum;
    public SumRange(int[] nums) {
        this.nums = nums;
        preSum = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}

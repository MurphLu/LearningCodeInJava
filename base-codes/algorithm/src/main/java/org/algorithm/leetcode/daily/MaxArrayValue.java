package org.algorithm.leetcode.daily;

/**
 * 给你一个下标从 0 开始、由正整数组成的数组 nums 。
 *
 * 你可以在数组上执行下述操作 任意 次：
 *
 * 选中一个同时满足 0 <= i < nums.length - 1 和 nums[i] <= nums[i + 1] 的整数 i 。将元素 nums[i + 1] 替换为 nums[i] + nums[i + 1] ，并从数组中删除元素 nums[i] 。
 * 返回你可以从最终数组中获得的 最大 元素的值。
 *
 * 前缀和 + 后缀和 - 当前数，取最大值
 */
public class MaxArrayValue {
    public static void main(String[] args) {
        new MaxArrayValue().maxArrayValue(new int[]{2,3,7,9,3});
    }

    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n];
        long[] postSum = new long[n];
        preSum[0] = nums[0];
        postSum[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            if(preSum[i - 1] <= nums[i]) {
                preSum[i] = preSum[i - 1] + nums[i];
            } else {
                preSum[i] = nums[i];
            }
            int revertIndex = n - 1 - i;
            if (postSum[revertIndex + 1] >= nums[revertIndex]) {
                postSum[revertIndex] = postSum[revertIndex + 1] + nums[revertIndex];
            }else {
                postSum[revertIndex] = nums[revertIndex];
            }
        }
        long max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(preSum[i] + postSum[i] - nums[i], max);
        }
        return max;
    }
}

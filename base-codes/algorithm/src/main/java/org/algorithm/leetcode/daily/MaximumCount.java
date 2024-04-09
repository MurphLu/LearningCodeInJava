package org.algorithm.leetcode.daily;

/**
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
 *
 * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
 * 注意：0 既不是正整数也不是负整数。
 */
public class MaximumCount {
    public static void main(String[] args) {
        int i = new MaximumCount().maximumCount(new int[]{
                -1, -1, -1, 0, 0, 1,1,1,1
        });
        System.out.println(i);
    }
    public int maximumCount(int[] nums) {
        if (nums[nums.length - 1] < 0 || nums[0] > 0) {
            return nums.length;
        }
        if (nums[nums.length - 1] == 0 && nums[0] == 0) {
            return 0;
        }
        int lastNag;
        int firstPos;

        int left = 0;
        int right = nums.length - 1;
        int mid = nums.length / 2;
        while (left < mid) {
            if (nums[mid] < 0) {
                left = mid;
            } else {
                right = mid;
            }
            mid = left + (right - left) / 2;
        }
        lastNag = left;
        left = 0;
        right = nums.length - 1;
        mid = left + (right - left) / 2;
        while (right > mid) {
            if (nums[mid] > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
        firstPos = right;

        return Math.max(lastNag + 1, nums.length - firstPos);
    }

}

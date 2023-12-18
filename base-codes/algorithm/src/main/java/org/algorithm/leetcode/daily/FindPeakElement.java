package org.algorithm.leetcode.daily;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 *
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 *
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,1};
        System.out.println(new FindPeakElement().findPeakElement(arr));
    }
    public int findPeakElement(int[] nums) {
        int index = process(0, nums.length - 1, nums);
        return nums[index];
//        return 0;
    }

    public int process(int start, int end, int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (end - start == 1) {
            return nums[end] > nums[start] ? end : start;
        }
        int middle = start + (end - start) / 2;
        if (nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]) {
            return middle;
        }
        if (nums[middle] < nums[middle + 1] ) {
            return process(middle, end, nums);
        } else {
            return process(start, middle, nums);
        }

    }
}

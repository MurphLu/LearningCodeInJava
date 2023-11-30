package org.algorithm.leetcode.topHot100;

public class FindDuplicate {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,4,5,2,6,7,2};
        System.out.println(findDuplicate(nums));
    }
    public static int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                if (nums[i] == nums[nums[i] - 1]) {
                    return nums[i];
                }
                swap(nums, i, nums[i] - 1);
            }
        }
        return 0;
    }
    private static void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }
}

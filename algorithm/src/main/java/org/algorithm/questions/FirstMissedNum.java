package org.algorithm.questions;

import java.util.Arrays;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 *
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 */
public class FirstMissedNum {
    public static void main(String[] args) {
        int[] arr = new int[]{2,1};
        System.out.println(new FirstMissedNum().firstMissingPositive(arr));
    }
    public int firstMissingPositive(int[] nums) {
        process(nums, 0);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i + 1;
            }
        }
        return nums.length;
    }

    public void process(int[] nums, int idx) {
        if (idx == nums.length) { return;}
        int cur = nums[idx];
        if (cur > nums.length || cur <= 0) {
            nums[idx] = 0;
        }
        while (cur <= nums.length && cur > 0 && cur != idx + 1) {
            swap(nums, idx, cur - 1);
            if(nums[idx] == cur) {
                nums[idx] = 0;
                break;
            }
            cur = nums[idx];
        }
        if (cur == idx + 1) {
            nums[idx] = cur;
        } else{
            nums[idx] = 0;
        }
        process(nums, idx + 1);
    }
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

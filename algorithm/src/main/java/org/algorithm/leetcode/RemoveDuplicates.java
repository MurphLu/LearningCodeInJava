package org.algorithm.leetcode;

import java.util.Arrays;
// 删除重复数，
// 有序数组
// 保证每个数在数组中最多出现两次，原地调整，
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] arr = new int[]{0,0,1,1,1,1,2,3,3};
        removeDuplicates(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int removeDuplicates(int[] nums) {
        int length = 1;
        int i = 1;
        while (i < nums.length) {
            if (nums[i]!= nums[length - 1]) {
                nums[length++] = nums[i++];
            } else if (nums[i] == nums[length - 1]){
                nums[length++] = nums[i++];
                while (i < nums.length && nums[i]== nums[length - 1]){
                    i++;
                }
            }
        }
        return length;
    }
}

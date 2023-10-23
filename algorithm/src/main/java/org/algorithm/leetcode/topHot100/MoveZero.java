package org.algorithm.leetcode.topHot100;

import java.util.Arrays;

public class MoveZero {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,13,3,5};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
    public static void moveZeroes(int[] nums) {
        int leftZero = 0;
        int rightZero = 0;
        int startIndex = 0;
        while (startIndex< nums.length && nums[startIndex] != 0) {
            startIndex++;
        }
        leftZero = startIndex;
        rightZero = startIndex;

        for (int i = startIndex + 1; i < nums.length && rightZero < nums.length; i++) {
            if (nums[i] == 0) {
                rightZero++;
            } else {
                swap(nums, leftZero++, i);
                if (leftZero > rightZero) {
                    rightZero++;
                }
            }
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}

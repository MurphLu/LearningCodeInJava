package org.algorithm.leetcode.topHot100;

import java.util.Arrays;

public class SortColors {
    public static void main(String[] args) {
        int[] arr = new int[]{2,0,2,1,1,0, 1,2,2,1,0,0};
        new SortColors().sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }
    public void sortColors(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int i = l;
        while (nums[l] == 0) {
            l++;
            i++;
        }
        while (nums[r] == 2) {
            r--;
        }

        while (i <= r) {
            if (nums[i] == 0) {
                if (i == l) {
                    while (nums[l] == 0) {
                        l++;
                        i++;
                    }
                }
                swap(nums, i, l);
                while (nums[l] == 0) {
                    l++;
                }
            } else if(nums[i] == 2) {
                swap(nums, i, r);
                while (nums[r] == 2) {
                    r--;
                }
            } else {
                i++;
            }
            if (i <= l) {
                i = l;
            }
        }
    }
    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}

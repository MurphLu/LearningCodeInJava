package org.algorithm.leetcode.daily;

import java.util.Arrays;
// 下一个排列
public class NextPermutation {
    public static void main(String[] args) {
        new NextPermutation().nextPermutation(new int[]{4,2,0,2,3,2,0});
    }
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        int l = n - 1;
        int r = -1;
        boolean meet = false;
        while (l > -1) {
            for (r = n; r > l; r--) {
                if (nums[l] < nums[r]) {
                    int temp = nums[l];
                    nums[l] = nums[r];
                    nums[r] = temp;
                    meet = true;
                }
                if (meet) break;
            }
            if(meet) break;
            l--;
        }

        if (l == -1) {
            sort(nums, 0);
        } else {
            sort(nums, l+1);
        }
        System.out.println(Arrays.toString(nums));
    }

    private void sort(int[] nums, int index) {
        for (int i = index; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1 ; j < nums.length; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
            }
            int temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
    }
}

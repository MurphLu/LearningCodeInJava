package org.algorithm.leetcode.topHot100;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RotateArr {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6};
        //1,2,3,4,5,6
        //1,2,3,4,1,6
        //1,2,5,4,1,6
        //3,2,5,4,1,6
        //3,2,5,4,1,2
        //3,4,5,6,1,2
        int k = 4;
        new RotateArr().rotate(nums, k);
        System.out.println(Arrays.toString(nums));
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        int temp;
        Set<Integer> set = new HashSet<>();
        while (i < k && i < n) {
            if (set.contains(i)) {
                i++;
                continue;
            }
            set.add(i);
            int tmpIdx = i;
            temp = nums[i];
            do {
                tmpIdx += k;
                if (tmpIdx >= n) {
                    tmpIdx %= n;
                    set.add(tmpIdx);
                }
                int innerTemp = nums[tmpIdx];
                nums[tmpIdx] = temp;
                temp = innerTemp;
            } while (tmpIdx != i);
        }

    }
}

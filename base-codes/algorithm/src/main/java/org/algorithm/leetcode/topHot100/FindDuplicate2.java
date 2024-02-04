package org.algorithm.leetcode.topHot100;

import java.util.BitSet;

public class FindDuplicate2 {
    public int findDuplicate(int[] nums) {
        boolean[] ba = new boolean[(int)Math.pow(10,5) + 1];
        for (int i = 0; i < nums.length; i++) {
            if (ba[nums[i]]) {
                return nums[i];
            }
            ba[nums[i]] = true;
        }
        return 0;
    }
}

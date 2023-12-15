package org.algorithm.presumAndIncDecSequence;

import java.util.Arrays;

public class PreSum {
    public static void main(String[] args) {

    }

    public void preSum(int[] nums) {
        int n = nums.length;
        int[] sumArr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sumArr[i + 1] = sumArr[i] + nums[i];
        }
        System.out.println(Arrays.toString(sumArr));
    }
}

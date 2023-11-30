package org.algorithm.leetcode.topHot100;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LengthOfLIS {
    public static void main(String[] args) {
        int[] arr = new int[]{10,9,2,5,3,7,101,18};
        lengthOfLIS(arr);
    }
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int curNum = nums[i];
            int count = 1;
            for (int j = i - 1; j >=0 ; j--) {
                int beforeNum = nums[j];
                if (beforeNum == curNum) {
                    count = Math.max(dp[j], count);
                } else if (beforeNum < curNum){
                    count = Math.max(dp[j] + 1, count);
                }
            }
            dp[i] = count;
            max = Math.max(max, count);
        }
        System.out.println(Arrays.toString(dp));
        return max;
    }
}

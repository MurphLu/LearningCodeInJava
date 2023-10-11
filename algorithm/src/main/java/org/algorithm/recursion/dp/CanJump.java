package org.algorithm.recursion.dp;

import java.util.Arrays;

public class CanJump {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,1,4};
        System.out.println(new CanJump().canJump(nums));
        System.out.println(new CanJump().canJump2(nums));
    }
    public boolean canJump(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            int step = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (i == 0 ) {
                    while (j <= i + step && j < nums.length){
                        dp[i][j++] = 1;
                    }
                } else {
                    int temp = j;
                    while (temp < nums.length - 1) {
                        dp[i][++temp] = dp[i-1][temp];
                    }
                    if (dp[i-1][j] != 0) {
                        while (j <= i + step && j < nums.length){
                            dp[i][j++] = 1;
                        }
                    }
                }
            }
        }
        for (int[] arr: dp
             ) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[nums.length - 1][nums.length - 1] == 1;
    }

    public boolean canJump2(int[] nums) {
        int maxTo = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxTo) {
                return false;
            }
            maxTo = Math.max(i + nums[i], maxTo);
            if (maxTo >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}

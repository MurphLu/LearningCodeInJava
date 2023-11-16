package org.algorithm.leetcode.daily;

public class LongestSubArray {
    public static void main(String[] args) {
        new LongestSubArray().longestAlternatingSubarray(new int[]{2,3,4,5}, 4);
    }
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int l = 0;
        int r = 0;
        int max = 0;
        while(r < nums.length) {
            if(nums[l] % 2 != 0 || nums[l] > threshold) {

                r++;
                l++;
                continue;
            }
            if(nums[r] <= threshold && ( l!=r && (nums[r] % 2)!= (nums[r-1] % 2)) ) {
                r++;
            } else {
                max = Math.max(max, r - l);
                l = r;
                l++;
                r++;
            }
        }
        max = Math.max(max, r - l);
        return max;
    }
}

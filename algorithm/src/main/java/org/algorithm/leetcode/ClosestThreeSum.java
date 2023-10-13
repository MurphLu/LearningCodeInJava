package org.algorithm.leetcode;

import java.util.Arrays;

public class ClosestThreeSum {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1,2};
        int target = 1;
        System.out.println(new ClosestThreeSum().threeSumClosest(arr, target));
        System.out.println(new ClosestThreeSum().threeSumClosest1(arr, target));
    }

    public int threeSumClosest(int[] nums, int target) {
        return process(nums, 0, 0, 0, target);
    }

    public int process(int[] nums, int index, int count, int sum, int target) {
        if (count > 3) {
            return -1;
        }
        if (index >= nums.length) {
            if (index == nums.length && count == 3) {
                return sum;
            }
            return -1;
        }
        if (count == 3) {
            return sum;
        }

        int take = process(nums, index + 1, count + 1, sum + nums[index], target);
        int skip = process(nums, index+1, count, sum, target);
        if (take == -1 && skip == -1) {
            return -1;
        } else {
            if (take == -1) {
                return skip;
            } else if (skip == -1) {
                return take;
            } else {
                return Math.abs(target - skip) > Math.abs(target - take) ? take : skip;
            }
        }
    }

    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int[] sums = new int[nums.length];
        Arrays.fill(sums, Integer.MAX_VALUE);
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int curSum = nums[i] + nums[start] + nums[end];
                if (target - curSum == 0) {
                    return target;
                }
                if (curSum > target) {
                    end --;
                } else {
                    start++;
                }
                if(sums[i] == Integer.MAX_VALUE){
                    sums[i] = curSum;
                } else {
                    sums[i] = Math.abs(target - curSum) < Math.abs(target - sums[i]) ? curSum : sums[i];
                }
            }
        }
        int result = sums[0];
        for (int i = 1; i < sums.length; i++) {
            if (sums[i] == -1){break;}
            result = Math.abs(target - result) < Math.abs(target - sums[i]) ? result : sums[i];
        }
        return result;
    }
}

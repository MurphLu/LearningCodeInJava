package org.algorithm.leetcode.daily;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 *
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的最少 删除次数。
 *
 */
public class MinimumMountainRemovals {
    public static void main(String[] args) {
        new MinimumMountainRemovals().minimumMountainRemovals(new int[]{100,92,89,77,74,66,64,66,64});
    }
    public int minimumMountainRemovals(int[] nums) {
//        System.out.println(process(nums));
        return nums.length - process(nums);
    }

    public int process(int[] nums) {
        int n = nums.length;
        int[] leftToRight = new int[n];
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(0);
        leftToRight[0] = 1;


        for (int i = 1; i < n; i++) {
            int current = nums[i];
            while (!list.isEmpty() && nums[list.getFirst()] >= current) {
                list.pop();
            }
            list.addFirst(i);
            int curMax = list.size();
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, leftToRight[j] + 1);
                }
            }
            leftToRight[i] = curMax;
        }

        list = new LinkedList<>();
        list.addFirst(n - 1);
        int[] rightToLeft = new int[n];
        rightToLeft[n - 1] = 1;
        for (int i = n - 2; i >= 0 ; i--) {
            int current = nums[i];
            while (!list.isEmpty() && nums[list.getFirst()] >= current) {
                list.pop();
            }
            list.addFirst(i);
            int curMax = list.size();
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, rightToLeft[j] + 1);
                }
            }
            rightToLeft[i] = curMax;
        }
        int max = 0;
        for (int i = 1; i < n - 1; i++) {
            if (rightToLeft[i] != 1 && leftToRight[i] != 1) {
                int count = rightToLeft[i] + leftToRight[i] - 1;
                max = Math.max(max, count);
            }
        }

        System.out.println(Arrays.toString(leftToRight));
        System.out.println(Arrays.toString(rightToLeft));
        return max;
    }
}

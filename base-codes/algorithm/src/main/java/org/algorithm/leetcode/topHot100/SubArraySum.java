package org.algorithm.leetcode.topHot100;

import java.util.*;

public class SubArraySum {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,1,2,1};
        subarraySum(arr, 3);
    }
    public static int subarraySum(int[] nums, int k) {
        int result = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int preLeft = sum - k;
            if (map.containsKey(preLeft)) {
                result += map.get(preLeft);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}

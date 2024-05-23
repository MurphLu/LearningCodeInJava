package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 如果子数组中所有元素都相等，则认为子数组是一个 等值子数组 。注意，空数组是 等值子数组 。
 *
 * 从 nums 中删除最多 k 个元素后，返回可能的最长等值子数组的长度。
 *
 * 子数组 是数组中一个连续且可能为空的元素序列。
 */
public class LongestEqualSubarray {
    public static void main(String[] args) {
        new LongestEqualSubarray().longestEqualSubarray(
                Arrays.asList(1,2,1),
                0
        );
    }
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int maxVal = nums.stream().max(Integer::compareTo).get();
        List<Integer>[] arr = new List[maxVal+1];
        for (int i = 0; i < nums.size(); i++) {
            int n = nums.get(i);
            List<Integer> list = arr[n];
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(i);
            arr[n] = list;
        }
        int max = 1;
        for(List<Integer> list : arr) {
            if (list != null) {
                max = Math.max(generateMaxLength(list, k), max);
            }
        }
        return max;
    }

    private int generateMaxLength(List<Integer> list, int k) {
        int startIdx = 0;
        int endIdx = 1;
        int max = 1;
        while (endIdx < list.size()) {
            if (list.get(endIdx) - list.get(startIdx) + 1 - (endIdx - startIdx + 1) <= k) {
                max = Math.max(endIdx - startIdx + 1, max);
                endIdx++;
            } else {
                startIdx++;
            }
        }
        return max;
    }
}

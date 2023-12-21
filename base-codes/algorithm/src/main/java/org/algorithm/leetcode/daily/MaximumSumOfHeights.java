package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。
 *
 * 你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。
 *
 * 如果以下条件满足，我们称这些塔是 美丽 的：
 *
 * 1 <= heights[i] <= maxHeights[i]
 * heights 是一个 山脉 数组。
 * 如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 数组：
 *
 * 对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
 * 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 *
 * 在 maxHeights 的基础上 只允许有一个峰值，求最大和
 */
public class MaximumSumOfHeights {
    public static void main(String[] args) {
        int[] arr = new int[]{5};
        List<Integer> list = new ArrayList<>();
        for(int i: arr) {
            list.add(i);
        }
        new MaximumSumOfHeights().maximumSumOfHeights(list);
    }
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        process(maxHeights);
        return 0L;
    }

    private long process(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] upFromLeft = new long[n];
        long[] upFromRight = new long[n];
        upFromLeft[0] = maxHeights.get(0);
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(0);
        for (int i = 1; i < n; i++) {
            int currentNum = maxHeights.get(i);
            while (!stack.isEmpty() && maxHeights.get(stack.getFirst()) > currentNum) {
                stack.pop();
            }
            long count = i - (stack.isEmpty() ? -1 : stack.getFirst()) ;
            long preIndex = i - count;
            upFromLeft[i] = (preIndex >= 0 ? upFromLeft[(int) preIndex] : 0)  +  currentNum * count;
            stack.addFirst(i);
        }
        stack = new LinkedList<>();
        upFromRight[n - 1] = maxHeights.get(n - 1);
        stack.addFirst(n - 1);
        for (int i = n - 2; i >= 0 ; i--) {
            int currentNum = maxHeights.get(i);
            while (!stack.isEmpty() && maxHeights.get(stack.getFirst()) > currentNum) {
                stack.pop();
            }
            long count = (stack.isEmpty() ? n : stack.getFirst()) - i;
            long preIndex = i + count;
            upFromRight[i] = (preIndex >= 0 ? upFromRight[(int) preIndex] : 0)  +  currentNum * count;
            stack.addFirst(i);
        }
        long max = 0;
        for (int i = 0; i < n - 1; i++) {
            max = Math.max(max, upFromLeft[i] + upFromRight[i] - maxHeights.get(i));
        }
        return max;
    }
}

package org.algorithm.leetcode.daily.monotonicStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1 。给你以一个整数数组 heights ，每个整数 互不相同，heights[i] 表示第 i 个人的高度。
 *
 * 一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人 矮 。
 * 更正式的，第 i 个人能看到第 j 个人的条件是 i < j 且 min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]) 。
 *
 * 请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到 的 人数 。
 */
public class CanSeePersonCount {
    public static void main(String[] args) {
        int[] arr = new int[]{5,3,2,1,1,2,3,1,2,3,5,3,2,1};
        int[] ints = new CanSeePersonCount().canSeePersonsCount(arr);
        System.out.println(Arrays.toString(ints));
    }
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (int i = 1; i < n; i++) {
            int top = stack.peek();
            while (heights[top] < heights[i]) {
                res[top]++;
                stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                top = stack.peek();
            }
            if (!stack.isEmpty()) {
                if (heights[top] == heights[i]) {
                    res[top]++;
                    stack.pop();
                } else {
                    res[stack.peek()]++;
                }

            }
            stack.add(i);
        }
        return res;
    }
}

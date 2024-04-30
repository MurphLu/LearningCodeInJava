package org.algorithm.leetcode.daily.monotonicStack;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
        int i = new LargestRectangleArea().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
        System.out.println(i);
    }
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        int max = 0;
        for (int i = 1; i < heights.length; i++) {
            if (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    int idx = stack.pop();
                    int val = 0;
                    if (stack.isEmpty()) {
                        val = heights[idx] * i;
                    } else {
                        val = heights[idx] * (i - 1 - stack.peek());
                    }
                    max = Math.max(val, max);
                }
            }
            stack.add(i);
        }
//        int endIdx =
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int val = 0;
            if (stack.isEmpty()) {
                val = heights[idx] * heights.length;
            } else {
                val = heights[idx] * (heights.length - stack.peek() - 1);
            }
            max = Math.max(val, max);
        }
        return max;
    }
}

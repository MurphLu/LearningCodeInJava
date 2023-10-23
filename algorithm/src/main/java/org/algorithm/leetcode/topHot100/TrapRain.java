package org.algorithm.leetcode.topHot100;
//栈...

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
 * 在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）
 */
public class TrapRain {
    public static void main(String[] args) {
        int[] arr = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(arr));
    }
    public static int trap(int[] height) {
        // 保持 stack 中维护数组的 index，保证其中的 index 在数组中的值单调递减
        Stack<Integer> stack = new Stack<>();
        // 将 0 ，也就是数组中第一个数直接入栈
        stack.add(0);
        int result = 0;
        // 从 1 开始遍历
        for (int i = 1; i < height.length; i++) {
            int cur = height[i];
            // 如果栈为空，或者栈顶 index 在数组中的只不比当前值小，那么当前 index 直接入栈
            if(stack.isEmpty() || height[stack.peek()] > cur) {
                stack.push(i);
            } else if (height[stack.peek()] == cur){
                stack.pop();
                stack.push(i);
            }else {
                // 当栈不为空，或者栈顶值小于当前值时
                while (!stack.isEmpty() && height[stack.peek()] < cur) {
                    // 先将栈顶值出栈
                    int last = stack.pop();
                    // 栈顶出栈后栈不为空，那么进行计算
                    if (!stack.isEmpty()) {
                        // 由栈的特性及之前的条件可知
                        // height[stack.peek()] <= height[last] < cur
                        // 所以 stack.peek() 与 i 在 last 之上有 x 格
                        // x = Math.min(height[stack.peek()], height[i]) - height[last]
                        // i 与 stack.peek() 中间有 y 格，y = i - steak.peek() - 1
                        // 所以 stack.peek() 与 i 之间，在 height[stack.peek()] 之上 有 x * y 的水量
                        // 轮询
                        result += (Math.min(height[stack.peek()], cur) - height[last]) * (i - stack.peek() - 1);
                    }
                }
                // 当栈为空，或者栈顶值大于等于当前值时，入栈
                stack.push(i);
            }
        }
        return result;
    }
}

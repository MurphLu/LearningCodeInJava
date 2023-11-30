package org.algorithm.monotoneStack;

import java.util.Stack;

// 无重复值，求数组中每个数左侧离他最近的比他大的数及右侧离他最近比他大的数
// 有重复值的话将值相同的 idx 组成一个小链表在 stack 中压在一起
public class MonotoneStack {
    Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        int[] nums = new int[]{5,1,4,3,6,2,9};
        new MonotoneStack().monotoneStack(nums);
    }

    public void monotoneStack(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int idx = stack.pop();
                System.out.println(
                        "left bigger: " + (stack.isEmpty() ? "null" : nums[stack.peek()]) +
                                ", current: " + nums[idx] + ", right bigger: " + nums[i]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            System.out.println(
                    "left bigger: " + (stack.isEmpty() ? "null" : nums[stack.peek()]) +
                            ", current: " + nums[idx] + ", right bigger: null");
        }
    }
}

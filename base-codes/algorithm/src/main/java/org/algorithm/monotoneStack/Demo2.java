package org.algorithm.monotoneStack;

// 定义:数组中累积和与最小值的乘积，假设叫做指标A。
// 给定一个数组，请返回子数组中，指标A最大的值。

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 每个数都以它自己为最小值，求它所在子数组的指标 A
 * 以每个数为参考点，得到两侧距离它最近的比它小的值，中间范围就是以该值作为最小点的子数组，单调栈求范围，然后分别求指标A，得到最大值
 */
public class Demo2 {
    Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        int[] nums = new int[]{5,1,4,3,6,2,9};
        List<RoundInfo> infoList = new Demo2().monotoneStack(nums);
        for (RoundInfo info : infoList) {
            System.out.println("minVal: " + nums[info.min] + ", A: " + info.getResult(nums));
        }
    }

    static class RoundInfo {
        int left;
        int right;
        int min;

        public RoundInfo(int left, int min, int right) {
            this.left = left < min ? left + 1 : left;
            this.right = right > min ? right - 1 : right;
            this.min = min;
        }

        public int getResult(int[] nums) {
            int sum = 0;
            for (int i = left; i <= right; i++){
                sum += nums[i];
            }
            return sum * nums[min];
        }

        @Override
        public String toString() {
            return "RoundInfo{" +
                    "left=" + left +
                    ", right=" + right +
                    ", min=" + min +
                    '}';
        }
    }

    public List<RoundInfo> monotoneStack(int[] nums) {
        List<RoundInfo> infoList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[stack.peek()] < nums[i]) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int idx = stack.pop();
                infoList.add(new RoundInfo(stack.isEmpty() ? idx : stack.peek(), idx, i));
                System.out.println(
                        "left bigger: " + (stack.isEmpty() ? "null" : nums[stack.peek()]) +
                                ", current: " + nums[idx] + ", right bigger: " + nums[i]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            infoList.add(new RoundInfo(stack.isEmpty() ? idx : stack.peek(), idx, idx));
            System.out.println(
                    "left bigger: " + (stack.isEmpty() ? "null" : nums[stack.peek()]) +
                            ", current: " + nums[idx] + ", right bigger: null");
        }
        return infoList;
    }
}

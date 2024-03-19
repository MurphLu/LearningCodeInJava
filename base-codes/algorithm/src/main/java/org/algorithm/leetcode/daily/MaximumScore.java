package org.algorithm.leetcode.daily;

import java.util.Stack;

/**
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 *
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 *
 * 请你返回 好 子数组的最大可能 分数 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,4,3,7,4,5], k = 3
 * 输出：15
 * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
 * 示例 2：
 *
 * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
 * 输出：20
 * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 2 * 104
 * 0 <= k < nums.length
 *
 * 单调栈
 */
public class MaximumScore {
    // 1,2,3,4,5,6,7,8,9,10
    // 1,2,3,1,4,3,4,5,9,1
    // 10, 4, 3, 10, 4, 15, 12, 10, 9, 10

    // 1, 4, 3, 7, 4, 5
    // 6, 4, 15, 7, 12, 5

    // 5,5,4,5,4,1,1,1
    // 10, 10, 20, 20, 8,8,8
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            int left = i;
            int right = i;
            while (left > 0) {
                if(nums[left - 1] < cur) {
                    break;
                }
                left --;
            }
            while (right < n - 1) {
                if (nums[right + 1] < cur) {
                    break;
                }
                right ++;
            }
            if (left <= k && right >= k) {
                max = Math.max((right - left + 1) * cur, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int i = new MaximumScore().maximumScore1(new int[]{5,5,4,5,4,1,1,1}, 0);
        System.out.println(i);
    }

    // 所有数字进栈一次，出栈一次
    public int maximumScore1(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (int i = 1; i < n; i++) {
            if (stack.isEmpty()) {
                stack.add(i);
            } else if (nums[stack.peek()] <= nums[i]) {
                stack.add(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    int poppedIdx = stack.pop();
                    int poppedNum = nums[poppedIdx];
                    int left = (stack.isEmpty() ? 0 : (stack.peek() + 1));
                    int right = i;
                    if (left <= k && right > k) {
                        max = Math.max(poppedNum * (right - left), max);
                    }
                }
                stack.add(i);
            }
        }
        int endIdx = 0;
        if (!stack.isEmpty()) {
            endIdx = stack.peek() + 1;
            while (!stack.isEmpty()){
                int poppedIdx = stack.pop();
                int poppedNum = nums[poppedIdx];
                int left = (stack.isEmpty() ? 0 : (stack.peek() + 1));
                if (left <= k && endIdx > k) {
                    max = Math.max(poppedNum * (endIdx - left), max);
                }
            }
        }
        return max;
    }
}

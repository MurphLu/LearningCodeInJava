package org.algorithm.leetcode.daily;

import java.util.*;
import java.util.function.IntFunction;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
 *
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 *
 * 请你返回你能得到的 最大得分 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-1,-2,4,-7,3], k = 2
 * 输出：7
 * 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
 * 示例 2：
 *
 * 输入：nums = [10,-5,-2,4,0,3], k = 3
 * 输出：17
 * 解释：你可以选择子序列 [10,4,3] （上面加粗数字），和为 17 。
 * 示例 3：
 *
 * 输入：nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * 输出：0
 */
public class MaxResult {
    public static void main(String[] args) {
        System.out.println(new MaxResult().maxResult3(
                new int[]{10,-5,-2,4,0,3},
                3
        ));
    }
    public int maxResult(int[] nums, int k) {
        List<int[]> nagRange = new ArrayList<>();
        int idx = 0;
        while (idx < nums.length) {
            boolean hasNag = false;
            int start = idx;
            int end = idx;
            if (nums[idx] >= 0) {
                idx ++;
            } else {
                while (idx < nums.length && nums[idx] < 0) {
                    hasNag = true;
                    end = idx;
                    idx++;
                }
            }
            if (hasNag){
                nagRange.add(new int[]{start, end});
            }
        }
        List<int[]> newRange = new ArrayList<>();
        for (int i = 0; i < nagRange.size(); i++) {
            int[] range = nagRange.get(i);
            if (range[1] - range[0] + 1 >= k ) {
                range[0] = range[0] == 0 ? range[0] : range[0] - 1;
                range[1] = range[1] == nums.length - 1 ? range[1] : range[1] + 1;
                newRange.add(range);
            }
        }
        List<int[]> mergedRange = new ArrayList<>();
        if (!newRange.isEmpty()) {
            mergedRange.add(newRange.get(0));
            for (int i = 1; i < newRange.size(); i++) {
                int[] current = newRange.get(i);
                int[] last = mergedRange.get(mergedRange.size() - 1);
                if (last[1] == current[0]) {
                    last[1] = current[1];
                } else {
                    mergedRange.add(current);
                }
            }
        }
        boolean containsFirst = false;
        boolean containLast = false;
        idx = 0;
        int sum = 0;
        Integer[] dp = new Integer[nums.length];
        while (idx < nums.length) {
            int[] r = null;
            if (!mergedRange.isEmpty()) {
                r = mergedRange.remove(0);
            }
            if (r == null) {
                r = new int[]{nums.length, nums.length};
            }
            while (idx < r[0]) {
                if (nums[idx] > 0) {
                    sum += nums[idx];
                    if(idx == 0) containsFirst = true;
                    if (idx == nums.length - 1) containLast = true;
                }
                idx++;
            }
            if (idx >= nums.length) {
                break;
            } else {
                if(r[0] == 0) containsFirst = true;
                if (r[1] == nums.length - 1) containLast = true;
                sum += process(nums, k, r[0], r[1], dp);
                idx = r[1] + 1;
            }
        }

        return sum + (containsFirst ? 0 : nums[0]) + (containLast ? 0 : nums[nums.length - 1]);
    }

    private int process(int[] nums, int k, int start, int end, Integer[] dp) {
        boolean isSame = true;
        int val = nums[start + 1];
        for (int i = start + 2; i < end - 1; i++) {
            if (val != nums[i]) {
                isSame = false;
                break;
            }
        }
        if (isSame) {
            return (end - 1 - start - 1) / k + nums[start] + nums[end];
        }
        if (start == end) {
            return nums[start];
        }
        int max = Integer.MIN_VALUE;
        if (dp[start] != null) {
            return dp[start];
        }
        for (int i = 1; i <= k && (i + start) <= end; i++) {
            max = Math.max(nums[start] + process(nums, k, start + i, end, dp), max);
        }
        dp[start] = max;
        return max;
    }
    // 0, 1, 2
    public int maxResult1(int[] nums, int k) {
        int[] dp = new int[nums.length];

        Queue<Integer> queue = new PriorityQueue<>((a, b) -> dp[b] - dp[a]);
        int index = 1;
        dp[0] = nums[0];
        queue.add(0);
        while (index < nums.length) {
            dp[index] = dp[queue.peek()] + nums[index];
            if (queue.size() >= k) {
                queue.remove(index - k);
            }
            queue.add(index);
            index++;
        }
        return dp[nums.length - 1];
    }

    // 双端单调队列
    public int maxResult3(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        LinkedList<Integer> list = new LinkedList<>();
        dp[0] = nums[0];
        list.addLast(0);
        for (int i = 1; i < n; i++) {
            while (list.getFirst() + k < i) {
                list.removeFirst();
            }
            int maxI = nums[i] + nums[list.getFirst()];
            dp[i] = maxI;
            while (!list.isEmpty() && dp[list.getLast()] < maxI ) {
                list.removeLast();
            }
            list.addLast(i);
        }
        return dp[nums.length - 1];
    }
}

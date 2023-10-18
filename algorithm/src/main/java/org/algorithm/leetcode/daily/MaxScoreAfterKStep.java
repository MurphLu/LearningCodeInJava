package org.algorithm.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 2530. 执行 K 次操作后的最大分数

 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。你的 起始分数 为 0 。
 *
 * 在一步 操作 中：
 *
 * 选出一个满足 0 <= i < nums.length 的下标 i ，
 * 将你的 分数 增加 nums[i] ，并且
 * 将 nums[i] 替换为 ceil(nums[i] / 3) 。
 * 返回在 恰好 执行 k 次操作后，你可能获得的最大分数。
 *
 * 向上取整函数 ceil(val) 的结果是大于或等于 val 的最小整数。
 */
public class MaxScoreAfterKStep {
    public static void main(String[] args) {
        int[] arr = new int[]{10,10,10,10,10};
        System.out.println(new MaxScoreAfterKStep().maxKElements(arr, 5));
    }

    private long maxKElements(int [] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for(Integer num: nums) {
            queue.add(num);
        }
        long sum = 0L;
        while (k > 0) {
            int c = queue.poll();
            queue.add((int)Math.ceil((double) c / 3.0));
            sum += c;
            k--;
        }
        return sum;
    }
}

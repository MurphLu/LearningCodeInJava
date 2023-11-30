package org.algorithm.leetcode.topHot100;

import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class FindKLargest {
    public static void main(String[] args) {

    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compareTo);

        for (int num: nums) {
            if (queue.size() < k) {
                queue.add(num);
            } else {
                if (queue.peek() < num) {
                    queue.poll();
                    queue.add(num);
                }
            }
        }
        return queue.peek();

    }
}

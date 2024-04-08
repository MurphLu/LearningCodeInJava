package org.algorithm.leetcode.daily.slideWindow;

import java.util.*;

/**
 * 给你一个整数数组 nums 。每一次操作中，你可以将 nums 中 任意 一个元素替换成 任意 整数。
 *
 * 如果 nums 满足以下条件，那么它是 连续的 ：
 *
 * nums 中所有元素都是 互不相同 的。
 * nums 中 最大 元素与 最小 元素的差等于 nums.length - 1 。
 * 比方说，nums = [4, 2, 5, 3] 是 连续的 ，但是 nums = [1, 2, 3, 5, 6] 不是连续的 。
 *
 * 请你返回使 nums 连续 的 最少 操作次数。
 *
 * 求 nums.length 长度范围内包含的对多不重复数字个数
 * 排序 + 去重 + 滑动窗口
 */
public class MinOperations {
    public static void main(String[] args) {

        int i = new MinOperations().minOperations1(new int[]{
               1,2,3,4
        });
        System.out.println(i);
    }
    public int minOperations(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        Map<Integer, Integer> set = new HashMap<>();
        for(int val : nums) {
            max = Math.max(max, val);
            min = Math.min(min, val);
            set.put(val, 1);
        }
        int size = nums.length;
        int left = min;
        int right = min;
        int maxCount = 1;
        while (right - left + 1 < size){
            right++;
            if (set.get(right)!= null){
                maxCount ++;
            }
        }
        int tempCount = maxCount;
        while (right < max) {

            if (set.get(left++)!= null){
                tempCount--;
            }

            if (set.get(++right)!= null) {
                tempCount++;
            }
            maxCount = Math.max(tempCount, maxCount);
        }
        return size - maxCount;
    }

    // 排序 + 滑动窗口
    public int minOperations1(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(nums[0]);
        int maxCount = 1;
        int currentIdx = 1;
        while (currentIdx < nums.length && (nums[currentIdx] - list.getFirst() + 1 <= nums.length)){
            if(nums[currentIdx] != list.getLast()) {
                list.addLast(nums[currentIdx]);
            }
            currentIdx++;
        }
        maxCount = list.size();
        while (currentIdx < nums.length){
            while (!list.isEmpty() && (nums[currentIdx] - list.getFirst() + 1 > nums.length)) {
                list.removeFirst();
            }
            if(list.isEmpty() || (nums[currentIdx] != list.getLast())) {
                list.addLast(nums[currentIdx]);
            }
            currentIdx++;
            maxCount = Math.max(list.size(), maxCount);
        }
        return nums.length - maxCount;
    }
}

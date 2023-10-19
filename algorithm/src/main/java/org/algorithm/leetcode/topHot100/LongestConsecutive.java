package org.algorithm.leetcode.topHot100;

// 最长数字连续序列
import java.util.*;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        int[] arr = new int[]{
                };
        System.out.println(longestConsecutive(arr));
    }
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (set.contains(cur)) {
                set.remove(cur);
                int count = 1;
                int bigger = cur + 1;
                int smaller = cur - 1;
                while (set.contains(bigger)) {
                    set.remove(bigger);
                    bigger ++;
                    count ++;
                }
                while (set.contains(smaller)) {
                    set.remove(smaller);
                    smaller--;
                    count++;
                }
                maxCount = Math.max(count, maxCount);
            }
        }
        return maxCount;
    }
}

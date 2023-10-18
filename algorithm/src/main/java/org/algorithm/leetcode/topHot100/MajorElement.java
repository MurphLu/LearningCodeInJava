package org.algorithm.leetcode.topHot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class MajorElement {
    public static void main(String[] args) {

    }

    // 比较容易想到的，map 统计词频
    // 时间复杂度O(n)，空间复杂度 O(n)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int majority = 0;
        int maxCount = 0;
        for(Integer num : nums) {
            int count = map.containsKey(num) ? map.get(num) + 1 : 1;
            if (maxCount < count) {
                maxCount = count;
                majority = num;
            }
            map.put(num, count);
        }
        return majority;
    }

    // 排序然后区中间数（中间数一定为众数）
    // 时间复杂度 O(nlogn) 空间复杂度 O(1)
}

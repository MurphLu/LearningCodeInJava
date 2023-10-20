package org.algorithm.leetcode.daily;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给你一个由 不同 正整数组成的数组 nums ，请你返回满足 a * b = c * d 的元组 (a, b, c, d) 的数量。其中 a、b、c 和 d 都是 nums 中的元素，且 a != b != c != d 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,3,4,6]
 * 输出：8
 * 解释：存在 8 个满足题意的元组：
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * 示例 2：
 *
 * 输入：nums = [1,2,4,5,10]
 * 输出：16
 * 解释：存在 16 个满足题意的元组：
 * (1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
 * (2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
 * (2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
 * (4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 */
public class TupleSameProduct {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,5,10};
        System.out.println(tupleSameProduct(arr));
    }

    public static int tupleSameProduct(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
        Arrays.sort(nums);
        int count = nums.length;
        int left = 0;
        int result = 0;
        while (left < count) {
            int right = count - 1;
            while (left < right) {
                int curMulti = nums[left] * nums[right];
                int cur = right - 1;
                while (cur > left) {
                    if (curMulti / nums[cur] >= nums[cur]){
                        break;
                    } else if (curMulti % nums[cur] == 0 && set.contains(curMulti / nums[cur])) {
                        result++;
                    }
                    cur--;
                }
                right--;
            }
            left++;
        }
        return result * 8;
    }

    public static int solution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // 统计不同两个数的乘积值结果出现的次数
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int prod = nums[i] * nums[j];
                map.put(prod, map.getOrDefault(prod, 0) + 1);
            }
        }
        int res = 0;
        // 根据乘积值出现次数计算组合数
        // 比如
        // 出现 1 次，说明没有符合要求的组合
        // 出现 2 次，则有一对，那么一对组合出的结果为 1 * 8
        // 出现 3 次，那么不考虑顺序，两两组合不同情况为 3 种，又因为一种组合就能出来 8 种不同的组合，则结果为 3 * 8，也就是 3 * （3 - 1）* 4
        // 出现 n 次，两两组合不同情况为 n * (n - 1) / 2，考虑顺序差异的话则有 n * (n - 1) / 2 * 8 = n * (n - 1) * 4
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            res += entry.getValue() * (entry.getValue() - 1) * 4;
        }
        return res;
    }
}

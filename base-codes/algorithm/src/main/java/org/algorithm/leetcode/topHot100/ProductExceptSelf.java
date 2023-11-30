package org.algorithm.leetcode.topHot100;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 *
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 */
public class ProductExceptSelf {
    public static void main(String[] args) {
        new ProductExceptSelf().productExceptSelf(new int[]{1,2,3,4});
    }
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] l = new int[n];
        int[] r = new int[n];
        l[0] = 1;
        r[0] = 1;
        for (int i = 0; i < n; i++) {
            l[i] = i == 0 ? 1 : l[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            r[i] = i == n - 1 ? 1 : r[i + 1] * nums[i + 1];
        }
        System.out.println(Arrays.toString(l));
        System.out.println(Arrays.toString(r));
        int[] result = new int[n];
        result[0] = r[0];
        result[n - 1] = l[n - 1];
        for (int i = 1; i < n - 1; i++) {
            result[i] = r[i] * l[i];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}

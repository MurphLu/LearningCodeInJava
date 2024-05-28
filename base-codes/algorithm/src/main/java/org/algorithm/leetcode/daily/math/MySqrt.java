package org.algorithm.leetcode.daily.math;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
public class MySqrt {
    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        int l = 0;
        int r = x;
        int mid = (l+r)/2;
        while (l < r - 1) {
            if (x / mid > mid) {
                l = mid;
            } else if(x / mid < mid) {
                r = mid;
            } else {
                return mid;
            }
            mid = (l+r)/2;
        }
        return l;
    }
}

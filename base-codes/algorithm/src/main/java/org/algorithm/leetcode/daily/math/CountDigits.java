package org.algorithm.leetcode.daily.math;

/**
 * 给你一个整数 num ，返回 num 中能整除 num 的数位的数目。
 *
 * 如果满足 nums % val == 0 ，则认为整数 val 可以整除 nums 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num = 7
 * 输出：1
 * 解释：7 被自己整除，因此答案是 1 。
 * 示例 2：
 *
 * 输入：num = 121
 * 输出：2
 * 解释：121 可以被 1 整除，但无法被 2 整除。由于 1 出现两次，所以返回 2 。
 * 示例 3：
 *
 * 输入：num = 1248
 * 输出：4
 * 解释：1248 可以被它每一位上的数字整除，因此答案是 4 。
 */
public class CountDigits {
    public static void main(String[] args) {
        System.out.println(countDigits(121));
    }
    public static int countDigits(int num) {
        int count = 0;
        int cur = num;
        while (cur > 0) {
            int curDigit = cur % 10;
            if(num % curDigit == 0) {
                count++;
            }
            cur /= 10;
        }
        return count;
    }

}

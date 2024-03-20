package org.algorithm.leetcode.daily;

/**
 * 给你一个正整数 p 。你有一个下标从 1 开始的数组 nums ，这个数组包含范围 [1, 2p - 1] 内所有整数的二进制形式（两端都 包含）。你可以进行以下操作 任意 次：
 *
 * 从 nums 中选择两个元素 x 和 y  。
 * 选择 x 中的一位与 y 对应位置的位交换。对应位置指的是两个整数 相同位置 的二进制位。
 * 比方说，如果 x = 1101 且 y = 0011 ，交换右边数起第 2 位后，我们得到 x = 1111 和 y = 0001 。
 *
 * 请你算出进行以上操作 任意次 以后，nums 能得到的 最小非零 乘积。将乘积对 109 + 7 取余 后返回。
 *
 * 注意：答案应为取余 之前 的最小值。
 *
 *
 *
 * 示例 1：
 *
 * 输入：p = 1
 * 输出：1
 * 解释：nums = [1] 。
 * 只有一个元素，所以乘积为该元素。
 * 示例 2：
 *
 * 输入：p = 2
 * 输出：6
 * 解释：nums = [01, 10, 11] 。
 * 所有交换要么使乘积变为 0 ，要么乘积与初始乘积相同。
 * 所以，数组乘积 1 * 2 * 3 = 6 已经是最小值。
 * 示例 3：
 *
 * 输入：p = 3
 * 输出：1512
 * 解释：nums = [001, 010, 011, 100, 101, 110, 111]
 * - 第一次操作中，我们交换第二个和第五个元素最左边的数位。
 *     - 结果数组为 [001, 110, 011, 100, 001, 110, 111] 。
 * - 第二次操作中，我们交换第三个和第四个元素中间的数位。
 *     - 结果数组为 [001, 110, 001, 110, 001, 110, 111] 。
 * 数组乘积 1 * 6 * 1 * 6 * 1 * 6 * 7 = 1512 是最小乘积。
 *
 * 最终结果为 maxVal * Math.pow((maxVal - 1),  (maxVal - 1) / 2)
 */
public class MinNonZeroProduct {
    // 0001，  1
    // 0010， 2
    // 0011， 3
    // 0100， 4
    // 0101， 5
    // 0110， 6
    // 0111， 7
    // 1000， 8
    // 1001， 9
    // 1010， 10
    // 1011， 11
    // 1100， 12
    // 1101， 13，
    // 1110， 14
    // 1111， 15
    public static void main(String[] args) {
        int i = new MinNonZeroProduct().minNonZeroProduct(30);
        System.out.println(i);
    }
    public int minNonZeroProduct(int p) {
        int mod = 1000000007;
        if (p == 1) return 1;
        if (p == 2) return 6;

        long max = (long)Math.pow(2, p) - 1;
        long time = max - 1;
        long count = time / 2;
        time = time % mod;
        long res = max % mod;
        long temp = time;
        long tempCount = 1;
        long currCount = 0;
        while (currCount < count) {
            if(tempCount * 2 + currCount < count) {
                tempCount *= 2;
                temp = (temp * temp) % mod;
            } else {
                currCount += tempCount;
                tempCount = 1;
                res = (res * temp) % mod;
                temp = time;
            }
        }
        return (int)res;
    }
}

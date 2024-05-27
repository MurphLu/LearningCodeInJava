package org.algorithm.leetcode;

/**
 * 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。
 * 观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 *
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 *
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。
 * 如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 *
 * k 个数字的 平均值 为这些数字求和后再除以 k 。
 *
 * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 *
 *
 * m == rolls.length
 * 1 <= n, m <= 10^5
 * 1 <= rolls[i], mean <= 6
 */
public class MissingRolls {
    public static void main(String[] args) {
        new MissingRolls().missingRolls(new int[]{1}, 3, 1);
    }
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int total = rolls.length + n;
        int totalRolls = mean * total;

        for (int roll: rolls) {
            totalRolls -= roll;
        }
        int[] nRolls = new int[n];
        if (process(totalRolls, nRolls)){
            return nRolls;
        }
        return new int[0];
    }

    private boolean process(int idx, int remain, int[] nRolls) {
        if (idx == nRolls.length && remain == 0) {
            return true;
        }
        if (remain < 0 || idx == nRolls.length || (remain > (nRolls.length - idx)*6) || (remain < (nRolls.length - idx))){
            return false;
        }

        for (int i = 1; i <= 6; i++) {
            nRolls[idx] = i;
            if (process(idx+1, remain-i, nRolls)){
                return true;
            }
        }
        return false;
    }

    // 只要能满足总数剩余值在可达范围内，
    //那么剩余的 roll 就可以使用 6 或者 1以及 1-6之间的一个数填充
    // 由于只要一种结果，那么该结果为效率最优解
    private boolean process(int remain, int[] nRolls) {
        int n = nRolls.length;
        if (remain > n * 6 || remain < n) {
            return false;
        }
        int idx = 0;
        while (remain > n) {
            if((remain - (n-1)) / 6 >= 1) {
                remain -= 6;
                nRolls[idx] = 6;
            } else {
                nRolls[idx] = remain - (n-1);
                remain -= nRolls[idx];
            }
            n--;
            idx++;
        }
        while (idx < nRolls.length) {
            nRolls[idx] = 1;
            idx++;
        }
        return true;
    }
}

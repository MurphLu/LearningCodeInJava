package org.algorithm.greedy;

import java.util.Arrays;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列.
 * 也不在同一条斜线上。
 * 给定一个整数n，返n皇后的摆法有多少种n=1，返回1。
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。
 * n=8，返回92.
 */
public class NQueen {
    public static void main(String[] args) {
//        int n = 1;
//        int[] record = new int[n];
//        System.out.println(process(0, record, n));
        System.out.println((1<<3) - 1);
        System.out.println(num(8));
    }

    /**
     *
     * @param i 当前行号
     * @param record 当前行之前的有效记录
     * @param n 总 列/行 数
     * @return 基于当前排列情况后续有效排列数
     */
    public static int process(int i, int[] record, int n) {
        // n - 1 为最后一行，如果到结尾，则说明有有效排列
        if (i == n) { return 1;}
        int res = 0;

        // 当前行放置位置 0 到 n-1
        for (int j = 0; j < n; j++) {
            // 基于当前行之前所放置的位置判断 当前行的当前位置是否有效，如果有效则继续，无效则判断下一个位置
            if (isValid(record, i, j)){
                // 将当前位置放入记录
                record[i] = j;
                // 继续寻找下一行合适位置，直到表尾位置，则说明为有效排列，返回 1，否则返回 0
                res += process(i+1, record, n);
            }
        }
        return res;
    }

    /**
     * 判断当前位置是否有效
     * @param records 当前行之前的记录
     * @param i 当前行 行号
     * @param j 当前列
     * @return 是否有有效位置
     */
    private static boolean isValid(int[] records, int i, int j) {
        for (int k = 0; k < i; k++) {

            if (
                    Math.abs(records[k] - j) == Math.abs(i - k)
                            ||
                    records[k] == j
            ) {
                return false;
            }
        }
        return true;
    }

    /**
     * n queen improve
     * @param n n < 32
     * @return
     */
    public static int num(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     *
     * @param limit
     * @param colLim 列的限制，1位置不能放，0位置可以
     * @param leftDiaLim  左斜线的限制
     * @param rightDiaLim 右斜线的限制
     * @return 有效排列个数
     */
    private static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        int pos = 0;
        int mosRightOne = 0;
        // colLim -- 已经占用的位置
        // leftDiaLim -- 当前行 左侧限制
        // rightDiaLim -- 当前行 右斜线限制
        // 取 ~(反) 并 &(与) 上 limit，则  pos 中 1 的位置为当前行可放置的位置
        pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;

        while (pos != 0) {
            // 取出最右侧的 1，也就是 当放置在最后一位 1 处时，通过 process2 计算之后每行可以放置的位置
            mosRightOne = pos & (~pos + 1);
            // 除去最后一位 1 之后还有多少有效位置，再进行下一轮操作
            pos = pos - mosRightOne;

            // 计算当放在取出的最右侧 1 位置时的之后的有效排列
            // colLim | mosRightOne 当前及之前行所占用的列的位置
            // leftDiaLim | mosRightOne leftDiaLim 当前行左侧限制 mosRightOne 取或，则为当前放置位置与左侧对角线占用情况的并集，左移 1 位则为下一行对角线占用情况
            // 比如 leftDiaLim = 0010000，mosRightOne = 0001000，那么下一行 leftDiaLim 则为 0011000 << 1 = 0110000
            // 右侧对角线同理
            res += process2(limit, colLim | mosRightOne, (leftDiaLim | mosRightOne) << 1, (rightDiaLim | mosRightOne) >>> 1);
        }
        return res;
    }
}

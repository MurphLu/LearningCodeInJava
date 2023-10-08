package org.algorithm.bitOperations;

/**
 * 位运算的题目
 * 之前介绍过一些，下面继续给定两个有符号32位整数a和b，返回a和b中较大的。
 * [要求]
 * 不用做任何比较判断
 */
public class CompWithoutComp {

    public static int flip(int n) {
        return n ^ 1;
    }

    /**
     * 非负数返回 1， 负数返回 0
     * @param n
     * @return
     */
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    /**
     * a - b 可能会溢出
     * @param a
     * @param b
     * @return
     */
    public static int getMax1(int a, int b) {
        int c = a - b; // c 为负 则 a < b 否则 a >= b;
        int scA = sign(c); // 看 c 的符号，如果 a >= b ，则 scA = 1
        int scB = flip(scA); // 如果 a >=b 则 scB = 0,
        // scA = 1 则 scB 为 0，此时 a>=b, 返回 a
        // scA = 0 则 scB = 1，此时 a<b 返回 b
        return scA * a + scB * b;
    }

    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a); // a 的符号
        int sb = sign(b); // b 的符号
        int sc = sign(c); // c 的符号。可能会溢出
        int difSab = sa ^ sb; // a 和 b 符号是否不同，不同则为 1
        int sameSab = flip(difSab);
        // 如果符号不同，可能会溢出，那么直接取 a 的符号，a 为正那么 returnA = 1
        // 如果符号相同，不会溢出，difSab = 0，则取差值来判断 returnA 是否为 1
        int returnA = difSab * sa + sameSab * sc;
        // 取 a 和 取 b 是互斥的
        int returnB = flip(returnA);

        // 返回
        return returnA * a + returnB * b;
    }
}


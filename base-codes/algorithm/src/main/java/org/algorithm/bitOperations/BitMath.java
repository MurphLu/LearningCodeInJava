package org.algorithm.bitOperations;

/**
 * 给定两个有符号32位整数a和b，不能使用算术运算符，分别实现a和b的加、减、乘、除运算
 * [要求]
 * 如果给定a、b执行加减乘除的运算结果就会导致数据的溢出，那么你实现的函数不必对此负责，除此之外请保证计算过程不发生溢出
 */
public class BitMath {
    public static void main(String[] args) {
        System.out.println(sum(10, 5));
        System.out.println(multiply(10, 5));
        System.out.println(division(15, 5));
    }

    public static int sum(int a, int b) {
        // a = 10101
        // b = 01101
        // nor = 11000
        // carry = 00101 << 1 = 01010;

        // next round
        // nor = 10010
        // carry = 10000;

        // next round
        // nor = 00010
        // carry = 10000 << 1 = 100000

        // next round
        // nor = 100010
        // carry = 00000
        // 结束 返回 100010

//        int nor = a ^ b; // 无进位相加结果
//        int carry = ((a & b) << 1); // 有进位的位置
//        while (carry != 0) {
//            int tempNor = nor ^ carry;
//            carry = ((nor & carry) << 1);
//            nor = tempNor;
//        }
//        return nor;

        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public static int minus(int a, int b) {
        return sum(a, negNum(b)); // a - b = a + (-b)
    }

    private static int negNum(int n) {
        return sum(~n, 1);
    }

    public static int multiply(int a, int b) {
        int result = 0;
        while (b != 0) {
            if ((b & 1) == 1) {
                result = sum(result, a);
            }
            a = a<<1;
            // >>>    :     无符号右移，忽略符号位，空位都以0补齐
            b = b>>>1;
        }
        return result;
    }

    private static boolean isNag(int n) {
        return n < 0;
    }

    public static int division(int a, int b) {
        int res = 0;
        int x = isNag(a) ? negNum(a) : a;
        int y = isNag(b) ? negNum(b) : b;
        for (int i = 31; i >= 0; i = minus(i, 1)) {
            if ((x>>i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNag(a) ^ isNag(b) ? negNum(res) : res;
    }
}

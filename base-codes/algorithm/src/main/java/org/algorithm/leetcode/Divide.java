package org.algorithm.leetcode;

/**
 * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
 *
 * 整数除法应该向零截断，也就是截去（truncate）其小数部分。例如，8.345 将被截断为 8 ，-2.7335 将被截断至 -2 。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的 商 。
 *
 * 注意：假设我们的环境只能存储 32 位 有符号整数，
 * 其数值范围是 [−2^31,  2^31 − 1] 。本题中，
 * 如果商 严格大于 2^31 − 1 ，则返回 2^31 − 1 ；
 * 如果商 严格小于 -2^31 ，则返回 -2^31 。
 *
 * -2^31 <= dividend, divisor <= 2^31 - 1
 * divisor != 0
 */
public class Divide {
    public static void main(String[] args) {
        System.out.println(new Divide().divide(-2147483648, 2));
    }

    int max = Integer.MAX_VALUE;
    int min = Integer.MIN_VALUE;
    public int divide(int dividend, int divisor) {

        int overVal = 0;

        // 只有当除数或被除数为 -2^31 时可能会溢出需要特殊处理

        // 都是 -2^31 返回 1
        if (divisor == min && dividend == min) return 1;
        // 除数是最小值，返回 0
        if (divisor == min) return 0;
        // 被除数为最小值：
        if (dividend == min) {
            //     除数为 -1：直接除会溢出，直接返回最大值
            if (divisor == -1) { return max;}
            else { //     其他情况：  保留 1，后续将其转为正数参与后边计算时再加回去，避免溢出
                dividend += 1;
                overVal = 1;
            }
        }


        // 查看符号，确定返回值符号
        boolean sameSym = dividend < 0 && divisor < 0 || dividend >= 0 && divisor > 0;
        // 都转为正数参与计算
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        // 如果除数小，直接返回 0；
        if (dividend < divisor) {
            return 0;
        }

        // 相等直接返回 1 或 -1
        if (dividend == divisor) {
            return sameSym ? 1 : -1;
        }

        // 进行计算，现找到 divisor 左移能到达的最大的小于 dividend 的数，然后减并右移动继续减
        int n = 0;
        int temp = 1;
        int tempDivisor = divisor;
        while (max - tempDivisor >= tempDivisor && dividend >= (tempDivisor<<1)) {
            tempDivisor <<= 1;
            temp <<= 1;
        }
        while (dividend >= divisor) {
            while (dividend < tempDivisor ) {
                tempDivisor >>= 1;
                temp >>= 1;
            }
            n += temp;
            dividend -= tempDivisor;
            // 进行一轮计算之后需要将之前保留的数加回来，并置零
            dividend += overVal;
            overVal = 0;
        }

        return sameSym ? n : -n;
    }

}

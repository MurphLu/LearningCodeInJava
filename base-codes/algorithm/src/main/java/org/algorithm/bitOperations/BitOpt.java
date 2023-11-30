package org.algorithm.bitOperations;
// 判断一个正整数是否为 2的幂，4的幂
public class BitOpt {
    public static void main(String[] args) {
        System.out.println(if2(1));
        System.out.println(if2(2));
        System.out.println(if2(3));
        System.out.println(if2(4));
        System.out.println(if2(5));
        System.out.println(if2(6));
        System.out.println(if2(7));
        System.out.println(if2(8));
        System.out.println(if4(16));
    }

    public static boolean if2(int n) {
        return (n & (n - 1)) == 0;
    }

    public static boolean if4(int n) {
        int i = 1;
        int pos = 0;
        while (pos < 16) {
            i = (i << 2) + 1;
            pos++;
        }

        System.out.println(i == 0x55555555);
        return (n & (n - 1)) == 0 && ((n & i) != 0);
    }
}

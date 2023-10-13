package org.algorithm.questions.intermediate;

/**
 * 给定一个函数f，可以1~5的数字等概率返回一个。请加工出1~7的数字等概率返回一个的函数g。
 * 给定一个函数f，可以a~b的数字等概率返回一个。请加工出c~d的数字等概率返回一个的函数g。
 * 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的函数g
 */
public class RandomNumber {
    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(fab(5,10));
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println(f01FromFab(0,5));
//        }
//        for (int i = 0; i < 100; i++) {
//            System.out.println(fcd(0, 5, 3, 10));
//        }
        for (int i = 0; i < 10; i++) {
            System.out.println(f01D());
        }
    }

    public static int f15() {
        return (int) (Math.random() * 5) + 1;
    }
    // 等概率返回 0 - 1
    // f05等概率返回 1, 2, 3, 4, 5
    // 如果f05返回 3 则重新调用
    // 如果 < 3 返回 0， 如果 > 3 返回 1
    public static int f01() {
        int res = 0;
        do {
            res = f15();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    public static int f17() {
        int res = 0;
        do {
            res += (f01()<<2) + (f01()<<1)+ f01();
        } while (res == 7);
        return res + 1;
    }

    public static int fab(int a, int b) {
        return (int)(Math.random() * (b - a + 1)) + a;
    }

    public static int f01FromFab(int a, int b) {
        int count = b - a + 1;
        Integer redo = null;
        if (count % 2 == 1) {
            redo = b;
        }
        int split = redo == null ? a + (b - a) / 2 : a + (b - 1 - a) / 2;
        int res;
        do {
            res = fab(a, b);
        } while (redo != null && res == redo);
        return res <= split ? 0 : 1;
    }

    public static int fcd(int a, int b, int c, int d) {
        int gap = d - c;
        int bitCount = 1;
        int top = 1;
        while (top < gap) {
            top = (top << 1) + 1;
            bitCount++;
        }
        int res = 0;
        do {
            res = 0;
            int temp = bitCount;
            while (--temp >=0) {
                res = (res + f01FromFab(a, b)) << 1;
            }
            res += f01FromFab(a, b);
        }while (res > gap);
        return res + c;
    }

    public static int f01D() {
        return (int)(Math.random() + 0.9);
    }

    public static int f01S() {
        int res;

        do {
            // res的结果
            // 00, 01, 10, 11
            // 出现 1 的概率为 p。出现 0 的概率为 1-p
            // 那么四个数的概率分别为
            // (1-p) * (1-p), (1-p)*p, p*(1-p), p*p
            // 01和1-的概率相同，那么只取这两种来分别返回 0 和 1
            res = (f01D() << 1) + f01D();
        }while (res == 0 || res == 3);
        return res == 1 ? 0 : 1;
    }

}

package org.algorithm.questions.intermediate;

/**
 * 假设s和m初始化，s ="a"; m = s;再定义两种操作，第一种操作:
 * m = s;
 * s = s + s;
 * 第二种操作
 * s = s + m;
 * 求最小的操作步骤数，可以将s拼接到长度等于n
 */
public class AddStrings {
    public static void main(String[] args) {
        // 20
        // 2 * 2 * 5
        // 第一次 m = a, s = aa 调用1，
        // 第二次 m = aa, s = aaaa 调用1，
        // 第三次 m = aaaa, s = aaaaaaaa 调用1
        // 之后调用 第二种操作三次，m = aaaa, s = aaaaaaaaaaaa, aaaaaaaaaaaaaaaa, aaaaaaaaaaaaaaaaaaaa
    }

    public static int[] divsSumAndCount(int n) {
        int sum = 0;
        int count = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                sum += i;
                count ++;
                n /= i;
            }
        }
        return new int[]{sum, count};
    }

    // 质数由第二种操作搞定，
    // 将 n 分解为质数因子的乘积
    public static int minOps(int n) {
        if (n < 2) {
            return 0;
        }
        if (isPrim(n)) {
            return n - 1;
        }
        int[] divSumAndCount = divsSumAndCount(n);
        return divSumAndCount[0] - divSumAndCount[1];
    }

    private static boolean isPrim(int n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

package org.algorithm.recursion.dp;


import java.util.Arrays;

// 数组中存有不同面值的可用钱币面额，将面值为 n 的钱找零，有多少种方法
public class TransMoney {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6};
        int rest = 100;
        System.out.println(process(arr, 0, rest));
        System.out.println(dpWay(arr, rest));
        System.out.println(dpImproveWay(arr, rest));
        System.out.println(dpImprove2Way(arr, rest));

    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int way = 0;
        for (int i = 0; arr[index] * i <= rest; i++) {
            way += process(arr, index+1, rest - arr[index] * i);
        }
        return way;
    }

    public static int dpWay(int[] arr, int money) {
        int dp[][] = new int[arr.length + 1][money + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int total = 0; total <= money; total++) {
                if (dp[index + 1][total] > 0) {
                    for (int zhang = 0; arr[index] * zhang <= money - total; zhang++) {
                        dp[index][total + arr[index] * zhang] += dp[index + 1][total];
                    }
                }
            }
        }
        return dp[0][money];
    }

    public static int dpImproveWay(int[] arr, int money) {
        int dp[][] = new int[arr.length + 1][money + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int total = 0; total <= money; total++) {
                if (dp[index + 1][total] > 0) {
                    for (int cur = 0; cur <= money - total; cur += arr[index]) {
                        dp[index][total + cur] += dp[index + 1][total];
                    }
                }
            }
        }
        return dp[0][money];
    }

    /**
     * 斜率优化，纯观察
     * 尝试版本 -> 记忆化搜索 -> 严格表结构动态规划 -> 精致版本动态规划
     */
    public static int dpImprove2Way(int[] arr, int money) {
        int dp[][] = new int[arr.length + 1][money + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            dp[index][0] = dp[index + 1][0];
            for (int total = 0; total <= money; total++) {
                dp[index][total] = dp[index+1][total];
                if (dp[index + 1][total] >= 0 && total - arr[index] >= 0) {
                    dp[index][total] += dp[index][total - arr[index]];
                }
            }
        }
        for (int[] arrr: dp) {
            System.out.println(Arrays.toString(arrr));
        }
        return dp[0][money];
    }
}

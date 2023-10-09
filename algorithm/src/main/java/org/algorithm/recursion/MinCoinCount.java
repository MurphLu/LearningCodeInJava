package org.algorithm.recursion;

// 一个正数数组，其中包含 n 个数字，代表 n 个硬币，每个硬币的面值可能相同也可能不同
// 给出一个目标金额，求最少多少枚硬币可以满足该目标金额

import java.lang.reflect.Array;
import java.util.Arrays;

public class MinCoinCount {
    public static void main(String[] args) {
        int[] coins = new int[]{2,7,3,5,3, 8, 10};
        System.out.println(minCoinCount1(coins, 18));
        System.out.println(minCoinCount2(coins, 18));
        System.out.println(minCoinCount3(coins, 18));
        System.out.println(minCoinCount3From1(coins, 18));
    }

    public static int minCoinCount1(int[] coins, int aim){
        return process(coins, 0, aim);
    }

    private static int process(int[] coins, int cur, int remain) {
        if (remain < 0) {
            return -1;
        }
        if (remain == 0) {
            return 0;
        }
        if (cur == coins.length) {
            return -1;
        }
        int getThis = process(coins, cur + 1, remain - coins[cur]);
        int skipThis = process(coins, cur + 1, remain);
        if (getThis == -1 && skipThis == -1) {
            return -1;
        } else {
            if (getThis == -1) {
                return skipThis;
            }
            if (skipThis == -1) {
                return getThis + 1;
            }
            return Math.min(getThis + 1, skipThis);
        }
    }

    public static int minCoinCount2(int[] coins, int aim){
        int[][] dp = new int[coins.length + 1][aim + 1];
        for (int[] arr: dp) {
            Arrays.fill(arr, -2);
        }
        return process2(coins, 0, aim, dp);
    }

    private static int process2(int[] coins, int cur, int aim, int[][] dp) {
        if (aim < 0) {
            return -1;
        }
        if (dp[cur][aim] != -2) {
            return dp[cur][aim];
        }
        if (aim == 0) {
            dp[cur][aim] = 0;
        } else if (cur == coins.length) {
            dp[cur][aim] = -1;
        } else {
            int getThis = process(coins, cur + 1, aim - coins[cur]);
            int skipThis = process(coins, cur + 1, aim);
            if (getThis == -1 && skipThis == -1) {
                dp[cur][aim] = -1;
            } else {
                if (getThis == -1) {
                    dp[cur][aim] = skipThis;
                }
                if (skipThis == -1) {
                    dp[cur][aim] = getThis + 1;
                }
                dp[cur][aim] = Math.min(getThis + 1, skipThis);
            }
        }
        return dp[cur][aim];
    }

    public static int minCoinCount3(int[] coins, int aim) {
        int[][] dp = new int[coins.length][aim + 1];
        for (int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        for (int index = 0; index < dp.length; index++) {
            if (index==0) {
                dp[index][aim - coins[index]] = 1;
                continue;
            }

            for (int remain = 0; remain < aim + 1; remain++) {
                int pre = dp[index - 1][remain];
                if (pre != -1) {
                    dp[index][remain] = pre;
                    int curRemainIfGetThis = remain - coins[index];
                    if (curRemainIfGetThis >= 0) {
                        if(dp[index][curRemainIfGetThis] == -1) {
                            dp[index][curRemainIfGetThis] = pre + 1;
                        } else {
                            dp[index][curRemainIfGetThis] = Math.min(dp[index][curRemainIfGetThis], pre + 1);
                        }
                    }
                }
            }
            if (aim - coins[index] >= 0) {
                dp[index][aim - coins[index]] = 1;
            }
        }
        for (int[] arr: dp) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[coins.length - 1][0];
    }

    public static int minCoinCount3From1(int[] coins, int remain) {
        int n = coins.length ;
        int[][] dp = new int[n + 1][remain + 1];
        for (int[] arr: dp) {
            arr[0] = 0;
        }
        for (int i = 1; i <= remain; i++) {
            dp[n][i] = -1;
        }

        for (int index = n - 1; index >= 0; index--){
            for (int rem = 1; rem <= remain; rem++) {
                int getThis = (rem - coins[index] < 0) ? -1 : dp[index + 1][rem - coins[index]];
                int skipThis = dp[index + 1][rem];
                if (getThis == -1 && skipThis == -1) {
                    dp[index][rem] = -1;
                } else {
                    if (getThis == -1) {
                        dp[index][rem] = skipThis;
                        continue;
                    }
                    if (skipThis == -1) {
                        dp[index][rem] = getThis + 1;
                        continue;
                    }
                    dp[index][rem] = Math.min(getThis + 1, skipThis);

                }
            }
        }
        for (int[] arr: dp) {
            System.out.println(Arrays.toString(arr));
        }
        return dp[0][remain];

    }
}

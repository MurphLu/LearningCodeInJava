package org.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你两个字符串 s 和 t ，统计并返回在 s 的 子序列 中 t 出现的个数，结果需要对 109 + 7 取模。
 */
public class NumDistinct {
    int mod = 1000000009;
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int[] arr:dp) {
            Arrays.fill(arr, -1);
        }
        return process(s, t, dp);
    }

    private int process(String s, String t, int[][] dp){
        if (t.isEmpty()) {
            return 1;
        }
        if (s.isEmpty() || s.length() < t.length()) {
            return 0;
        }
        if (dp[s.length()][t.length()] != -1) {
            return dp[s.length()][t.length()];
        }
        int res = 0;
        res = (res + process(s.substring(1), t, dp)) % mod;
        if (s.charAt(0) == t.charAt(0)) {
            res = (res + process(s.substring(1), t.substring(1), dp)) % mod;
        }
        dp[s.length()][t.length()] = res % mod;
        return dp[s.length()][t.length()];
    }
}

package org.algorithm.leetcode;

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 */

import java.util.Arrays;

public class MinDistance {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int[] arr: dp) {
            Arrays.fill(arr, -1);
        }
        return process(word1, word2, dp);
    }

    public int process(String word1, String word2, int[][] dp) {
        if (word1.isEmpty() && word2.isEmpty()) {
            return 0;
        }
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }
        if (dp[word1.length()][word2.length()] != -1) {
            return dp[word1.length()][word2.length()];
        }
        int insert = 1 + process(word1, word2.substring(1), dp);
        int cnt = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
        int change = cnt + process(word1.substring(1), word2.substring(1), dp);
        int delete = 1 + process(word1.substring(1), word2, dp);
        int res = Math.min(insert, change);
        res = Math.min(res, delete);
        dp[word1.length()][word2.length()] = res;
        return res;
    }
}

package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinExtraChar {
    public static void main(String[] args) {
        String s = "sayhelloworld";
        String[] dict = new String[]{"hello","world"};
        new MinExtraChar().minExtraChar(s, dict);
    }

    static class Info {
        String s;
        int startIdx;
        int endIdx;

        public Info(String s, int startIdx) {
            this.s = s;
            this.startIdx = startIdx;
            this.endIdx = startIdx + s.length();
        }

        @Override
        public String toString() {
            return "Info{" +
                    "s='" + s + '\'' +
                    ", startIdx=" + startIdx +
                    ", endIdx=" + endIdx +
                    '}';
        }
    }
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            dp[i] = dp[i - 1];
            for(String sunS : dictionary) {
                int subLen = sunS.length();
                if (subLen <= i && sunS.equals(s.substring(i - subLen, i))){
                    dp[i] = Math.max(dp[i], dp[i-subLen]+subLen);
                }
            }
        }
        return n - dp[n];
    }
}


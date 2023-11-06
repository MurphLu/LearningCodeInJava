package org.algorithm.leetcode;

import java.util.Arrays;

public class LongestValidParentheses {
    public static void main(String[] args) {
        //          00240024
        String s = "(())(())(())((()))";
        System.out.println(longestValidParentheses(s));
    }
    public static int longestValidParentheses(String s) {
        int[] log = new int[s.length()];
        int count = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ')') {
                if (count == 0 ) {
                    log[i] = 0;
                } else {
                    log[i] = log[i - 1] + 2;
                    if (i - log[i] >=0) {
                        log[i] += log[i-log[i]];
                    }
                    max = Math.max(max, log[i]);
                    count--;
                }
            } else {
                count++;
            }

        }
        return max;
    }
}

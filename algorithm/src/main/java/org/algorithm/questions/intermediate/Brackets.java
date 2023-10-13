package org.algorithm.questions.intermediate;

import java.util.Arrays;

public class Brackets {
    public static void main(String[] args) {
        maxLength("(()()))()()");
    }
    // 括号字符串，判断加多少个括号才能使括号字符串完整（有效）
    public static int need(String str) {
        int count = 0; // right
        int ans = 0; // left
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                count ++;
            } else {
                if (count == 0) { // 当前是")" 且前面括号匹配，那么需要在该 ")" 前加一个 "（"
                    ans ++;
                }else { // 当前是")" 且 count 大于 0，则多一对匹配的括号，count--
                    count --;
                }
            }
        }
        return count + ans;
    }

    // 最长有效括号字符串
    public static int maxLength(String s) {
        int[] dp = new int[s.length()];
        int pre = 0;
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (s.charAt(i) ==')') {
                pre = i - dp[i-1] - 1;
                if (pre >=0 && s.charAt(pre) == '(') {
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return res;
    }
}

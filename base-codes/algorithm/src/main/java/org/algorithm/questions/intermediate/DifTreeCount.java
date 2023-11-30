package org.algorithm.questions.intermediate;

import java.util.Arrays;

// 给定一个非负整数n，代表二叉树的节点个数。返回能形成多少种不同的二叉树结构（节点无值）
public class DifTreeCount {
    public static void main(String[] args) {
        System.out.println(process(5));
        System.out.println(processRes(5));
    }

    public static int processRes(int n) {
        if (n < 0){ return 0; }
        if (n == 0){ return 1; }
        if (n == 1){ return 1; }
        if (n == 2) {return 2;}
        int result = 0;
        for (int i = 0; i <= n - 1; i++) {
            int left = processRes(i);
            int right = processRes(n - i - 1);
            result += left * right;
        }
        return result;
    }

    public static int process(int n) {
        if (n == 0) {return 0;}
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < n + 1; i++) {
            int left = 0;
            int right = i - 1;
            int result = 0;
            while (left < i) {
                result += dp[left] * dp[right];
                left ++;
                right --;
            }
            dp[i] = result;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}

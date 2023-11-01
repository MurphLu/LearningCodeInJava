package org.algorithm.leetcode.daily;

import java.util.Arrays;

public class MaxArea {
    public static void main(String[] args) {
//        System.out.println(maxArea(10000000000L, 10000000000L));
    }

    public static int maxArea(long h, long w, int[] horizontalCuts, int[] verticalCuts) {
        long mod = (int)Math.pow(10, 9) + 7;
        long maxWidth = 0;
        long maxHeight = 0;
        long lastH = 0;
        long lastW = 0;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        for (int i = 0; i < horizontalCuts.length; i++) {
            maxHeight = Math.max(horizontalCuts[i] - lastH, maxHeight);
            lastH = horizontalCuts[i];
        }
        maxHeight = Math.max(h - lastH, maxHeight) % mod;
        for (int i = 0; i < verticalCuts.length; i++) {
            maxWidth = Math.max(verticalCuts[i] - lastW, maxWidth);
            lastW = verticalCuts[i];
        }

        maxWidth = Math.max(w - lastW, maxWidth) % mod;
        long result = maxHeight * maxWidth % mod;
        return (int)result;
    }
}

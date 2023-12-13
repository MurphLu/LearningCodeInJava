package org.algorithm.leetcode.daily;

public class MakeSmallestPalindrome {
    public String makeSmallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char l = chars[left];
            char r = chars[right];
            if (l < r) {
                chars[right] = l;
            } else if (l > r) {
                chars[left] = r;
            }
            left ++;
            right --;
        }
        return new String(chars);
    }
}

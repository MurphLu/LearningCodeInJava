package org.algorithm.leetcode;

/**
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 *
 * 字母和数字都属于字母数字字符。
 *
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println('b' - 'B');
        System.out.println((char)('B'+32));
    }
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char l = parseToCap(s.charAt(left));
            char r = parseToCap(s.charAt(right));
            if(!isEC(l)) {
                left++;
                continue;
            }
            if (!isEC(r)) {
                right--;
                continue;
            }
            if(l != r) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isEC(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }
    private char parseToCap(char c) {
        if (c >= 'A' && c<='Z') {
            return (char) (c+32);
        }
        return c;
    }
}



package org.algorithm.leetcode.daily;

/**
 * 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
 *
 * 例如，str == ["abc", 3] =="abcabcabc" 。
 * 如果可以从 s2 中删除某些字符使其变为 s1，则称字符串 s1 可以从字符串 s2 获得。
 *
 * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
 * 现在给你两个字符串 s1 和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
 *
 * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1 获得。
 */

public class GetMaxRepetitions {
    public static void main(String[] args) {
        int maxRepetitions = new GetMaxRepetitions().getMaxRepetitions("acb", 2, "acb", 2);
        System.out.println(maxRepetitions);

    }
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int s1P = 0;
        int s2P = 0;
        int s1L = s1.length();
        int s2L = s2.length();
        char[] charS1 = s1.toCharArray();
        char[] charS2 = s2.toCharArray();
        int count = 0;
        for (int i = 0; i < n1; i++) {
            s1P = 0;
            if (i != 0 && s2P == 0) {
                count = n1 / i * count + (n1 % i == 0 ? 0 : (n1 % i) * count / i);
                break;
            }
            while (s1P < s1L) {
                if(charS1[s1P] == charS2[s2P]){
                    s2P++;
                    if (s2P == s2L) {
                        s2P = 0;
                        count++;
                    }
                }
                s1P++;
            }
        }
        return count / n2;
    }
}

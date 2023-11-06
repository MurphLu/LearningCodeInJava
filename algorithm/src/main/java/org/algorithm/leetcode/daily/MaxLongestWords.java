package org.algorithm.leetcode.daily;

import java.util.*;

public class MaxLongestWords {
    public static void main(String[] args) {
        System.out.println(maxProduct(new String[]{"abcw","baz","foo","bar","xtfn","abcdef"}));
    }

    public static int maxProduct(String[] words) {
//        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        int[] bitmap = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char[] cs = word.toCharArray();
            int digI = 0;
            for (char c : cs) {
                digI |= (1 << c-'a');
            }
            bitmap[i] = digI;
        }


        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((bitmap[i] & bitmap[j]) == 0) {
                    result = Math.max(words[i].length() * words[j].length(), result);
                    int a = 0b00001;
                }
            }
        }
        return result;
    }
}

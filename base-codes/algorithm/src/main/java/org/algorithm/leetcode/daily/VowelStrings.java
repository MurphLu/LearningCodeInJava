package org.algorithm.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

public class VowelStrings {

    public static int vowelStrings(String[] words, int left, int right) {
        Set<Character> cs = new HashSet<>();
        cs.add('a');
        cs.add('e');
        cs.add('i');
        cs.add('o');
        cs.add('u');
        int count = 0;
        for (int i = left; i <= right && i < words.length; i++) {
            String word = words[i];
            if (cs.contains(word.charAt(0)) && cs.contains(word.charAt(word.length() - 1))) {
                count++;
            }
        }
        return count;
    }
}

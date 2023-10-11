package org.algorithm.slidingWindow;

import java.util.HashSet;
import java.util.Set;

public class StringQues {
    public static void main(String[] args) {
        System.out.println(new StringQues().lengthOfLongestSubstring("aabaab!bb"));
        System.out.println(StringQues.lengthOfLastWord("   fly me   to   the moon  "));
    }

    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        int start = 0;
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (set.contains(chars[i])) {
                length = Math.max(set.size(), length);
                while (chars[start] != chars[i]){
                    set.remove(chars[start]);
                    start++;
                }
                start++;
//                set.remove(chars[i]);
                continue;
            }
            set.add(chars[i]);
        }
        return Math.max(set.size(), length);
    }

    public static int lengthOfLastWord(String s) {
//        char[] chars = s.toCharArray();
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ' ' && length == 0){ continue; }
            if (length > 0 && c == ' '){ break; }
            length ++;
        }
        return length;
    }
}

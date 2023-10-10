package org.algorithm.slidingWindow;

import java.util.HashSet;
import java.util.Set;

public class longestSubStr {
    public static void main(String[] args) {
        System.out.println(new longestSubStr().lengthOfLongestSubstring("aabaab!bb"));
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
}

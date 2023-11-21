package org.algorithm.leetcode.daily;

import java.util.*;

public class MinWindow {
    public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC", "ABC"));
    }
    public String minWindow(String s, String t) {
        Map<Character, Integer> dic = getDic(t);
        int count = 0;
        int start = 0;
        int end = 0;
        String result = "";
        while (end < s.length()) {
            char endC = s.charAt(end);
            if (start == end && !dic.containsKey(endC)) {
                start++;
                end++;
                continue;
            }
            if (dic.containsKey(endC)){
                if (dic.get(endC) > 0) {
                    count++;
                }
                dic.put(endC, dic.get(endC) - 1);
            }
            end++;

            while (count == t.length() || !dic.containsKey(s.charAt(start))){
                System.out.println(s.substring(start, end));
                char startC = s.charAt(start);
                if(dic.containsKey(startC)) {
                    dic.put(startC, dic.get(startC) + 1);
                    if (dic.get(startC) == 1) {
                        result = !result.isEmpty() && result.length() < end - start ? result : s.substring(start, end);
                        count--;
                    }

                }
                start++;
            }

        }
        return result;
    }
    public Map<Character, Integer> getDic(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c: t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}

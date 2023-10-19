package org.algorithm.leetcode.topHot100;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            if (map.containsKey(s)) {
                map.get(s).add(str);
            } else {
                List<String> cur = new ArrayList<>();
                cur.add(str);
                map.put(s, cur);
            }
        }
        return new ArrayList<>(map.values());
    }
}

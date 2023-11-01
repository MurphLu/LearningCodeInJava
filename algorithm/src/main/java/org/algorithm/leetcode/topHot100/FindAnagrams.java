package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAnagrams {
    public static void main(String[] args) {
        System.out.println(findAnagrams("abaacbabc","abc"));
    }
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            Character c = p.charAt(i);
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> collection = new HashMap<>();
        int curStartIndex = 0;
        int currentLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!charMap.containsKey(c)) {
                collection.clear();
                curStartIndex = i + 1;
                currentLength = 0;
            } else {
                boolean addResult = tryAdd(charMap, collection, c);
                char curChar = s.charAt(curStartIndex);
                if (addResult) {
                    currentLength++;
                    if (currentLength == p.length()){
                        result.add(curStartIndex);
                        collection.put(curChar, collection.get(curChar) - 1);
                        currentLength--;
                        curStartIndex++;
                    }
                } else {
                    while (curChar != c) {
                        curStartIndex++;
                        collection.put(curChar, collection.get(curChar) - 1);
                        curChar = s.charAt(curStartIndex);
                        currentLength--;
                    }
                    curStartIndex++;
                }
            }
        }
        return result;
    }

    private static boolean tryAdd(Map<Character, Integer> charMap, Map<Character, Integer> collection, Character c) {
        if (!collection.containsKey(c) || collection.get(c) < charMap.get(c)) {
            collection.put(c, collection.getOrDefault(c, 0) + 1);
            return true;
        } else {
            return false;
        }
    }
}

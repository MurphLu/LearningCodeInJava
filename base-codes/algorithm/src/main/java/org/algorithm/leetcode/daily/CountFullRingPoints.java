package org.algorithm.leetcode.daily;

import java.util.*;

public class CountFullRingPoints {
    public static void main(String[] args) {

    }
    public int countPoints(String rings) {
        Set<Character> fullSet = new HashSet<>();
        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < rings.length(); i+=2) {
            Character index = rings.charAt(i + 1);
            Character c = rings.charAt(i);
            Set<Character> cs = map.getOrDefault(index, new HashSet<>());
            cs.add(c);
            if (cs.size() == 3) {
                fullSet.add(index);
            }
            map.put(index, cs);
        }
        return fullSet.size();
    }
}

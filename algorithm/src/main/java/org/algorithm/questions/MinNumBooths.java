package org.algorithm.questions;

import java.util.HashMap;
import java.util.Map;

public class MinNumBooths {
    public int minNumBooths(String[] demand) {
        Map<Character, Integer> map = new HashMap<>();
        for (String s : demand) {
            Map<Character, Integer> dayMap = new HashMap<>();
            char[] day = s.toCharArray();
            for (Character d : day) {
                int count = dayMap.getOrDefault(d, 0);
                dayMap.put(d, count + 1);
            }
            for (Character c : dayMap.keySet()) {
                int count = 0;
                if (map.containsKey(c)) {
                    count = map.get(c);
                }
                map.put(c, Math.max(count, dayMap.get(c)));
            }
        }
        int sum = 0;
        for (Integer i: map.values()) {
            sum += i;
        }
        return sum;
    }
}

package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {

    private Map<Character, String> getKeyMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        return map;
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Character, String> keyMap = getKeyMap();
        List<String> res = new ArrayList<>();
        res.add("");
        for (char c: digits.toCharArray()) {
            String s = keyMap.get(c);
            res = processDigits(s, res);
        }
        return res;
    }

    private List<String> processDigits(String s, List<String> list) {
        List<String> newRes = new ArrayList<>();
        for (char c: s.toCharArray()) {
            for(String gen: list){
                newRes.add(gen+c);
            }
        }
        return newRes;
    }
}

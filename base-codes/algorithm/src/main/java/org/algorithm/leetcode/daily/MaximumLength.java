package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 给你一个仅由小写英文字母组成的字符串 s 。
 *
 * 如果一个字符串仅由单一字符组成，那么它被称为 特殊 字符串。例如，字符串 "abc" 不是特殊字符串，而字符串 "ddd"、"zz" 和 "f" 是特殊字符串。
 *
 * 返回在 s 中出现 至少三次 的 最长特殊子字符串 的长度，如果不存在出现至少三次的特殊子字符串，则返回 -1 。
 *
 * 子字符串 是字符串中的一个连续 非空 字符序列。
 */
public class MaximumLength {
    public static void main(String[] args) {
        new MaximumLength().maximumLength("aada");
    }
    public int maximumLength(String s) {
        Map<String, Integer> record = new HashMap<>();
        Map<Character, List<String>> charMap = new HashMap<>();
        int idx = 0;
        while (idx < s.length()) {
            int end = idx+1;
            while (end < s.length() && s.charAt(idx) == s.charAt(end)) {
                end++;
            }

            String subStr = s.substring(idx, end);
            char c = subStr.charAt(0);
            record.put(subStr, record.getOrDefault(subStr, 0) + 1);
            List<String> cl = charMap.getOrDefault(c, new ArrayList<>());
            cl.add(subStr);
            charMap.put(c, cl);
            idx = end;
        }
        int max = 0;
        for(Map.Entry<String, Integer> entry: record.entrySet()) {
            String str = entry.getKey();
            int cnt = entry.getValue();
            List<String> sl = charMap.get(str.charAt(0));
            for(String subString: sl) {
                if (subString.length() > str.length()) {
                    cnt += (subString.length() - str.length() + 1) * record.get(subString);
                }
            }
            if (cnt >= 3) {
                max = Math.max(str.length(), max);
            } else if (cnt == 2) {
                max = Math.max(str.length() - 1, max);
            } else {
                max = Math.max(str.length() - 2, max);
            }

        }
        return max == 0 ? -1 : max;
    }

}

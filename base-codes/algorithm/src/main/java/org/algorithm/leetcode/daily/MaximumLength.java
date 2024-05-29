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
        new MaximumLength().maximumLength("cccerrrecdcdccedecdcccddeeeddcdcddedccdceeedccecde");
    }
    public int maximumLength(String s) {
        Map<String, Integer> count = new HashMap<>();
        Map<Character, Integer> charCount;
        for (int i = 1; i <= s.length() - 2; i++) {
            int cnt = 0;
            StringBuilder sb = new StringBuilder(s.substring(0, i));
            charCount = new HashMap<>();
            for(char c: sb.toString().toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
            if (charCount.size() == 1) {
                count.put(sb.toString(), 1);
                cnt++;
            }
            for (int j = i; j < s.length(); j++){
                char cRemove = sb.charAt(0);
                charCount.put(cRemove, charCount.get(cRemove)-1);
                if (charCount.get(cRemove) == 0) {
                    charCount.remove(cRemove);
                }
                sb.deleteCharAt(0);
                char c = s.charAt(j);
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
                sb.append(c);
                if (charCount.size() == 1) {
                    String str = sb.toString();
                    count.put(str, count.getOrDefault(str, 0)+1);
                    cnt++;
                }
            }
            if (cnt < 3) {
                break;
            }
        }
        System.out.println(count);
        int maxLength = 0;
        int strCount = 0;
        for(Map.Entry<String, Integer> entry : count.entrySet()) {
            if(entry.getValue() >=3) {
                int keyLength = entry.getKey().length();
                if (keyLength > maxLength) {
                    maxLength = keyLength;
                }
            }
        }
        return maxLength;
    }

}

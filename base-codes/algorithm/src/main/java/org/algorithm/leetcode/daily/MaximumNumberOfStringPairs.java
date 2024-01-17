package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 给你一个下标从 0 开始的数组 words ，数组中包含 互不相同 的字符串。
 *
 * 如果字符串 words[i] 与字符串 words[j] 满足以下条件，我们称它们可以匹配：
 *
 * 字符串 words[i] 等于 words[j] 的反转字符串。
 * 0 <= i < j < words.length
 * 请你返回数组 words 中的 最大 匹配数目。
 *
 * 注意，每个字符串最多匹配一次。
 */
public class MaximumNumberOfStringPairs {

    public int maximumNumberOfStringPairs(String[] words) {
        Set<String> unpicked = new HashSet<>();
        for(String word:words) {
            String reversed = reversed(word.toCharArray());
            if (unpicked.contains(reversed)){
                unpicked.remove(reversed);
            } else {
                unpicked.add(word);
            }
        }
        return (words.length - unpicked.size()) / 2;
    }

    private String reversed(char[] s){
        char[] newS = new char[s.length];
        List<Character> lc = new ArrayList<>();
        for (int i = s.length; i > 0 ; i--) {
            newS[s.length - i] = s[i - 1];
        }
        return new String(newS);
    }
}

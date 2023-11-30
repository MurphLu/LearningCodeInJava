package org.algorithm.slidingWindow;

import java.util.*;

/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 *
 *  s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 *
 * 例如，如果 words = ["ab","cd","ef"]，
 * 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 */
public class SubStrings {
    public static void main(String[] args) {
//        System.out.println(findSubstring("abaababbaba", new String[]{"ab","ba","ab","ba"}));
        System.out.println(find("barfoofoobarthefoobarman",  new String[]{"bar","foo","the"}));
    }

    public static List<Integer> find(String s, String[] words) {
        Map<String, Integer> wordsMap = new HashMap<>();
        // 字频表
        for (String word: words) {
            wordsMap.put(word, wordsMap.containsKey(word) ? wordsMap.get(word) + 1 : 1);
        }
        int wordLength = words[0].length();
        List<Integer> list = new ArrayList<>();
        // 进行 word 长度次 process, 开始位置分别为 [0..<word.length]
        for (int i = 0; i < wordLength; i++) {
            process(list, wordsMap, i, s, words);
        }
        return list;
    }

    /**
     * 由于会从分别 [0..< word.length] 的位置开始调用 process，
     * 所以 process 中遍历的步长为 wordLength,
     * 这样就已经覆盖了所有可能性
     * @param list
     * @param map
     * @param start
     * @param str
     * @param words
     */
    public static void process(List<Integer> list, Map<String, Integer> map, int start, String str, String[] words) {
        int wordCount = words.length;
        int wordLength = words[0].length();
        Map<String, Integer> curMap = new HashMap<>();
        int end = start + wordCount * wordLength;
        while (end <= str.length()) {
            int count = 0;
            // 从 start 到 end 遍历，步长为 wordLength
            for (int i = start; i < end && end <= str.length(); i+=wordLength) {
                String curSub = str.substring(i, i + wordLength);
                if (map.containsKey(curSub)) { // 如果 i...i+wordLength 位置的字符串在 words中
                    // 那么继续判断是否存在于 当前遍历的词频表中
                    // 如果当前子字符串不在当前词频表，或者当前字符串在词频表中的数量小于需要的次数，
                    // 那么直接更新词频表，并将当前 count++
                    if (!curMap.containsKey(curSub) || curMap.get(curSub) < map.get(curSub)) {
                        curMap.put(curSub, curMap.containsKey(curSub) ? curMap.get(curSub) + 1 : 1);
                        count ++;
                    } else {
                        // 如果在，并且当前词频表中已经到达了需要的次数，那么说明当前状态不能满足，
                        // 此时需要找到最早放到词频表中的当前子串，一直遍历到最早扔到词频表中的当前子串
                        // 并从找到的子串之后继续
                        String prefix = str.substring(start, start + wordLength);
                        while (end <= str.length() && !prefix.equals(curSub)) {
                            start += wordLength;
                            end += wordLength;
                            curMap.put(prefix, curMap.get(prefix) - 1);
                            count --;
                            prefix = str.substring(start, start + wordLength);
                        }
                        start += wordLength;
                        end += wordLength;
                    }
                    if (count == words.length) {
                        list.add(start);
                        String prefix = str.substring(start, start + wordLength);
                        start += wordLength;
                        end += wordLength;
                        curMap.put(prefix, curMap.get(prefix) - 1);
                        count --;
                    }
                } else { // 如果不在，那说明当前字符串加上当前轮遍历过的字符串不满足，直接跳到当前字符串之后重置状态开始遍历
                    start += wordLength * (count + 1);
                    end += wordLength * (count + 1);
                    count = 0;
                    curMap.clear();
                }
            }
        }
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int wordsCount = words.length;
        int wordSize = words[0].length();
        if (s.length() < wordsCount * wordSize) {
            return list;
        }
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word: words) {
            wordMap.put(word, wordMap.containsKey(word) ? wordMap.get(word) + 1 : 1);
        }
        int start = 0;
        int end = wordsCount * wordSize;
        while (end <= s.length()) {
            Map<String, Integer> innerMap = new HashMap<>();
            if (!wordMap.containsKey(s.substring(start, start + wordSize))) {
                start += 1;
                end += 1;
                continue;
            }
            int totalSize = 0;
            for (int innerStart = start; innerStart < end &&  end <= s.length(); innerStart += wordSize) {
                String curSub = s.substring(innerStart, innerStart + wordSize);
                if (wordMap.containsKey(curSub)) {
                    if (!innerMap.containsKey(curSub)) {
                        innerMap.put(curSub, 1);
                        totalSize += 1;
                    } else {
                        if(innerMap.get(curSub).equals(wordMap.get(curSub))) {
                            start += 1;
                            end += 1;
                            break;
                        } else {
                            innerMap.put(curSub, innerMap.get(curSub) + 1);
                            totalSize += 1;
                        }
                    }
                    if (totalSize == words.length) {
                        list.add(start);
                        String first = s.substring(start, start + wordSize);
                        innerMap.put(first, innerMap.get(first) - 1);
                        start += 1;
                        end += 1;
                        break;
                    }
                } else {
                    end += 1;
                    start += 1;
                    break;
                }
            }

        }
        return list;
    }
}

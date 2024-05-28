package org.algorithm.leetcode.daily.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 注意:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 *
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] 由小写英文字母和符号组成
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class FullJustify {
    String[] spaceMap;



    public List<String> fullJustify(String[] words, int maxWidth) {
        spaceMap = spaceMap(maxWidth);
        List<String> res = new ArrayList<>();
        buildLines(words, maxWidth, 0, maxWidth, new ArrayList<>(), res);
        return res;
    }

    private void buildLines(String[] words, int maxWidth, int idx, int remainWidth,  List<String> rec, List<String> records) {

        String word = words[idx];
        if (remainWidth < word.length()) {
            records.add(buildLine(rec, remainWidth + 1));
            rec = new ArrayList<>();
            remainWidth = maxWidth;
        }
        rec.add(word);
        if (idx == words.length-1) {
            String s = buildLastLine(rec, remainWidth - word.length());
            records.add(s);
            return;
        }
        buildLines(words, maxWidth, idx+1, remainWidth - words[idx].length() - 1, rec, records);
    }

    private String buildLine(List<String> rec, int remainWidth){
        StringBuilder sb = new StringBuilder();
        if (rec.size() == 1) {
            sb.append(rec.get(0));
            sb.append(spaceMap[remainWidth]);
            return sb.toString();
        }
        int avg = remainWidth / (rec.size() - 1);
        int idx = 0;
        while (idx < rec.size()){
            sb.append(rec.get(idx));
            if (idx < rec.size() - 1) {
                sb.append(" ");
                sb.append(spaceMap[avg]);
                if (remainWidth % (rec.size() - 1) != 0) {
                    sb.append(" ");
                    remainWidth--;
                }
            }
            idx++;
        }
        return sb.toString();
    }

    private String buildLastLine(List<String> rec, int remainWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rec.size(); i++) {
            sb.append(rec.get(i));
            if (i < rec.size() - 1) {
                sb.append(" ");
            }
        }

        if(remainWidth > 0){
            sb.append(spaceMap[remainWidth]);
        }
        return sb.toString();
    }

    private String[] spaceMap(int maxWidth) {
        String[] map = new String[maxWidth];
        map[0] = "";
        int i = 1;
        while (i < maxWidth) {
            map[i] = map[i-1] + " ";
            i++;
        }

        return map;
    }
}

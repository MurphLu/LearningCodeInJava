package org.algorithm.leetcode.daily.string;

import org.algorithm.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class StringQuestions {
    public static void main(String[] args) {
        System.out.println(new StringQuestions().finalString("string"));
    }

    /**
     * 2810. 故障键盘
     * 你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
     *
     * 给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。
     *
     * 返回最终笔记本屏幕上输出的字符串。
     *
     * @param s 1 <= s.length <= 100， 小写字母，s[0] != 'i'
     * @return 最终结果
     */
    public String finalString(String s) {
        char wrongChar = 'i';
        List<Character> lc = new ArrayList<>();
        char[] sc = s.toCharArray();
        lc.add(sc[0]);
        int index = 1;
        while (index < sc.length) {
            if (sc[index] == wrongChar) {
                index ++;
                if (index < sc.length && sc[index] == wrongChar) {
                    index ++;
                } else {
                    ListUtils.reverse(lc);
                }
            } else {
                lc.add(sc[index]);
                index ++;
            }
        }
        char[] res = new char[lc.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = lc.get(i);
        }
        return new String(res);
    }
}

package org.algorithm.stringAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 最长回文子串
public class Manacher {
    public static void main(String[] args) {
        new Manacher().manacher("123454321123454321abcabcababbbabb");
    }

    public void manacher(String str) {
        char[] handled = handle(str);
        int r = 0; // 遍历过的回文最右右边界
        int c = 0; // 拥有最右右边界回文的中点
        // 每个位置的最大半径
        int[] maxRadius = new int[handled.length];
        // 因为 0 位置为插入字符，0 位置的最大回文半径是 0;
        maxRadius[0] = 0;
        // 从 1 位置开始遍历
        int i = 1;
        while (i < handled.length) {
            // 当右边界
            if (i >= r) {
                int radius = 0;
                int left = i;
                int right = i;
                while ( ++ right < handled.length &&  -- left >= 0 && handled[left] == handled[right]) {
                    radius += 1;
                }
                maxRadius[i] = radius;
                if (i + radius > r) {
                    r = i + radius;
                    c = i;
                }
            } else {
                int i2 = c - (i - c);
                int radius2 = maxRadius[i2];
                if (i2 - radius2 > c - maxRadius[c]) {
                    maxRadius[i] = radius2;
                } else if (i2 - radius2 < c - maxRadius[c]) {
                    int curRadius = maxRadius[c] - (c - i2);
                    maxRadius[i] = curRadius;
                } else {
                    int curRadius = maxRadius[i2];
                    int left = i - curRadius;
                    int right = i + curRadius;
                    while ( ++ right < handled.length &&  -- left >= 0 && handled[left] == handled[right]) {
                        curRadius += 1;
                    }
                    maxRadius[i] = curRadius;
                }
            }
            i++;
        }
        List<String> subStrings = new ArrayList<>();
        for (int j = 0; j < maxRadius.length; j++) {
            int radius = maxRadius[j];
            int startIdx = j - radius;
            int endIdx = j + radius;
            StringBuilder s = new StringBuilder();
            while (startIdx < endIdx) {
                if (startIdx % 2 != 0) {
                    s.append(handled[startIdx]);
                }
                startIdx ++;
            }
            if (s.length() == 0) {
                continue;
            }
            subStrings.add(s.toString());
        }
        System.out.println(subStrings);
    }

    public char[] handle(String str) {
        char[] chars = str.toCharArray();
        char[] result = new char[str.length() * 2 + 1];
        for (int i = 0; i < chars.length; i++) {
            result[i * 2] = '#';
            result[i * 2 + 1] = chars[i];
        }
        result[result.length - 1] = '#';
        return result;
    }
}

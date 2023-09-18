package org.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

// 数组中字符串拼接成最小的字符串
public class MinStringValue {
    public static void main(String[] args) {
        String[] strings = new String[]{"a", "ab", "b", "ba"};
        System.out.println(minStr(strings));
    }

    public static String minStr(String[] strings) {
        Arrays.sort(strings, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder builder = new StringBuilder();
        for (String s: strings) {
            builder.append(s);
        }
        return builder.toString();
    }
}

package org.algorithm.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 1 -> A
 * 2 -> B
 * ....
 * 26 -> Z
 *
 * 那么给定一个只含数字的字符串，转成字母字符串，有多少种方法
 */
public class NumLetterConvert {

    public static void main(String[] args) {
        convert("11126");
    }
    private static Map<String, String> dic;
    static {
        dic = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            dic.put(String.valueOf(i + 1), Character.toString(i + 'A'));
        }
    }
    public static void convert(String num) {
        process(num, "");
    }

    public static void process(String num, String res) {
        if (num.isEmpty()) {
            System.out.println(res);
            return;
        }
        String s1 = num.substring(0, 1);
        if (s1.equals("0")) {
            return;
        }
        String s2 = null;
        String convertVal = null;
        if (num.length() > 1 && (s1.equals("1") || s1.equals("2"))) {
            s2 = num.substring(0, 2);
            convertVal = dic.get(s2);
        }
        process(num.substring(1), res + dic.get(s1));
        if (s2 != null && convertVal != null) {
            process(num.substring(2), res + convertVal);
        }
    }
}

package org.algorithm.leetcode;

/**
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 *
 * 部分无效数字列举如下：["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 *
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 */

public class IsNumber {
    public static void main(String[] args) {
        String[] s = new String[]{"+.", "3."};
        for (String c: s
             ) {
            System.out.println(new IsNumber().isNumber(c));
        }
//        boolean number = new IsNumber().isNumber("-123.456e789");
//        System.out.println(number);
    }
    public boolean isNumber(String s) {
        if (s.isEmpty() || (s=removeSymbol(s)).isEmpty()){
            return false;
        }
        int symbolCnt = 0;
        int pointCnt = 0;
        int expCnt = 0;
        int numCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c== '-') {
                if (i == s.length() - 1) {
                    return false;
                }
                symbolCnt++;
                continue;
            }
            if (c == 'e' || c== 'E') {
                expCnt++;
                continue;
            }
            if (c == '.') {
                pointCnt++;
                continue;
            }
            if(!isNumChar(c)) {
                return false;
            }
            numCnt++;
        }
        if (symbolCnt > 1 || pointCnt > 1 || expCnt > 1 || numCnt == 0) {
            return false;
        }

        if (expCnt == 1) {
            String e = s.contains("e") ? "e" : "E";
            if((s = handleExp(s, e)) == null) {
                return false;
            }
        }

        if (pointCnt == 1) {
            if (s.equals(".")) {
                return false;
            }
            String[] split = s.split("\\.");
            for(String sp:split) {
                while (!sp.isEmpty() && isNumChar(sp.charAt(0))) {
                    sp = sp.substring(1);
                }
                if (!sp.isEmpty()) {
                    return false;
                }
            }
            return true;
        } else {
            while (!s.isEmpty() && isNumChar(s.charAt(0))) {
                s = s.substring(1);
            }
            return s.isEmpty();
        }

    }

    private String handleExp(String s, String e){
        String[] split = s.split(e);
        if (split.length == 1 || split[0].isEmpty()) {
            return null;
        }
        String expPart = removeSymbol(split[1]);
        if (expPart.isEmpty()) {
            return null;
        }
        while (!expPart.isEmpty() && isNumChar(expPart.charAt(0))) {
            expPart = expPart.substring(1);
        }
        return expPart.isEmpty() ? split[0] : null;
    }



    private boolean isNumChar(char c) {
        return c >='0' && c <= '9';
    }

    private String removeSymbol(String s) {
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            return s.substring(1);
        }
        return s;
    }
}

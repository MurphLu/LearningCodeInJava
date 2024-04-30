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
        // 统计非数字字符的个数
        // +-号最多有两个，完整数字一个，E 后可以有一个
        int symbolCnt = 0;
        // . 最多只能有一个
        int pointCnt = 0;
        // e 或 E 只能有一个
        int expCnt = 0;
        // 数字字符，如果为 0 那一定不是数字
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

        // 判断个数不符合的话直接返回 false
        if (symbolCnt > 1 || pointCnt > 1 || expCnt > 1 || numCnt == 0) {
            return false;
        }

        // 如果有 e 或者 E
        if (expCnt == 1) {
            // 判断具体是哪个 e
            String e = s.contains("e") ? "e" : "E";
            // e 要保证前其后只能是一个 +/- 后边跟的要都是数字，且必须有数字
            // handleExp 中判断 e 之后是否符合要求，且 e 之前不能为空，不符合返回空
            // 符合返回 e 之前字符用来做之后的判断
            if((s = handleExp(s, e)) == null) {
                return false;
            }
        }

        // 如果有 .
        if (pointCnt == 1) {
            // 只有一个 . 那一定直接返回 false
            if (s.equals(".")) {
                return false;
            }
            // 用 . 分割，此时字符串中有且只有一个 . 然后再判断其他字符是否都为数字字符即可
            // 犹豫只要只有 . 和数字，那么 . 所在的位置就无所谓，直接用 . 分割并判断即可
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
            // 没有 . 的情况直接判断字符串中只有数字字符即可
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

package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个输入字符串 ( s) 和一个字符模式 ( p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 */
public class IsMatch {
    public static void main(String[] args) {
        boolean aa = new IsMatch().isMatch("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb",
                "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb");
        System.out.println(aa);
    }
    public boolean isMatch(String s, String p) {
        List<String> pattern = getPattern(p);
        System.out.println(pattern);
        int fixedLength = 0;
        for (int i = 0; i < pattern.size(); i++) {
            String pa = pattern.get(i);
            if (!pa.equals("*")) {
                fixedLength += pa.length();
            }
        }
        return isMatch(s, pattern, 0, fixedLength, new int[s.length()+1][pattern.size()+1]);
    }

    private boolean isMatch(String s, List<String> pattern, int idx, int fixedLength, int[][] dp) {
        if (dp[s.length()][idx] == -1) {
            return false;
        }
        if (s.isEmpty()) {
            if(idx == pattern.size()) {
                return true;
            } else if(idx < pattern.size()) {
                for (int i = idx; i < pattern.size(); i++) {
                    if (!pattern.get(i).equals("*")) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        if (idx >= pattern.size()) {
            return false;
        }
        String currentPat = pattern.get(idx);
        if (currentPat.equals("?")) {
            return isMatch(s.substring(1), pattern, idx + 1, fixedLength - 1, dp);
        } else if (currentPat.equals("*")) {
            for (int i = 0; i <= s.length() - fixedLength; i++) {
                if(isMatch(s.substring(i), pattern, idx + 1, fixedLength, dp)){
                    return true;
                }
            }
            dp[s.length()][idx] = -1;
        } else {
            if(s.indexOf(currentPat) == 0) {
                return isMatch(s.substring(currentPat.length()), pattern, idx + 1, fixedLength - currentPat.length(), dp);
            }
        }
        dp[s.length()][idx] = -1;
        return false;
    }

    private List<String> getPattern(String p) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '?' || c == '*') {
                String s = sb.toString();
                if (!s.isEmpty()) {
                    res.add(s);
                }
                if (res.isEmpty() || !res.get(res.size() - 1).equals("*") || c != '*') {
                    res.add(new String(new char[]{c}));
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        String s = sb.toString();
        if (!s.isEmpty()) {
            res.add(s);
        }
        return res;
    }


}

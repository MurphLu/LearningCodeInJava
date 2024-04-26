package org.algorithm.leetcode.reg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class IsMatch {
    public static void main(String[] args) {
        String s = "....aa..aa.*.*a*.*";

        System.out.println(Arrays.toString(s.split("\\*")));
        System.out.println("aa".indexOf("a"));
        System.out.println(new IsMatch().isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*"));
    }
    public boolean isMatch(String s, String p) {
        List<String> pArr = new ArrayList<>();
        handleReg(p, pArr);
        System.out.println(pArr);
        return process(s, pArr, 0);
    }

    private void handleReg(String regs, List<String> regSplit) {
        if (regs.isEmpty()) {
            return;
        }
        if (regs.charAt(0) == '.') {
            int nextStart = 0;
            if (regs.length() > 1 && regs.charAt(1) == '*') {
                regSplit.add(".*");
                nextStart = 2;
            } else {
                regSplit.add(".");
                nextStart = 1;
            }
            handleReg(regs.substring(nextStart), regSplit);
        } else if (regs.length() == 1) {
            regSplit.add(regs);
        } else if (regs.charAt(1) == '*'){
            regSplit.add(regs.substring(0, 2));
            handleReg(regs.substring(2), regSplit);
        } else {
            int start = 0;
            int end = 0;
            while (end < regs.length() && regs.charAt(end) != '.' && regs.charAt(end) != '*'){
                end++;
            }
            if (start == end) {
                regSplit.add(regs.substring(0,1));
                handleReg(regs.substring(1), regSplit);
            } else {
                if (end >= regs.length()) {
                    regSplit.add(regs);
                    return;
                }
                if (regs.charAt(end) == '*') {
                    end-=1;
                }
                regSplit.add(regs.substring(start, end));
                handleReg(regs.substring(end), regSplit);
            }

        }
    }


    private boolean process(String s, List<String> regSplit, int idx) {
        if (s.isEmpty()) {
            while (idx < regSplit.size()) {
                String reg = regSplit.get(idx);
                if(reg.contains("*")) {
                    idx ++;
                } else {
                    break;
                }

            }
            return idx >= regSplit.size();
        }
        if (idx >= regSplit.size()){
            return false;
        }

        String curReg = regSplit.get(idx);
        if (curReg.equals(".")) {
            return process(s.substring(1), regSplit, idx+1);
        } else if(curReg.equals(".*")) {
            for (int i = 0; i <= s.length(); i++) {
                if(process(s.substring(i), regSplit, idx+1)){
                    return true;
                }
            }
        } else if (curReg.length() == 2 && curReg.charAt(1) == '*') {
            char c = curReg.charAt(0);
            int i = 0;
            if(process(s.substring(i), regSplit, idx+1)){
                return true;
            }
            while (i < s.length() && s.charAt(i) == c) {
                i++;
                if(process(s.substring(i), regSplit, idx+1))
                    return true;
            }
        } else {
            if(s.indexOf(curReg) == 0) {
                return process(s.substring(curReg.length()), regSplit, idx+1);
            } else {
                return false;
            }
        }
        return false;
    }
}

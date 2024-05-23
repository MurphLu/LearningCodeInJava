package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
 *
 * 例如， "ACGAATTCCG" 是一个 DNA序列 。
 * 在研究 DNA 时，识别 DNA 中的重复序列非常有用。
 *
 * 给定一个表示 DNA序列 的字符串 s ，返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。你可以按 任意顺序 返回答案。
 */
public class FindRepeatedDnaSequences {
    public static void main(String[] args) {
        new FindRepeatedDnaSequences().findRepeatedDnaSequences("AAAAAAAAAAA");
    }
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        int[] rec = new int[1048576]; // 2^20
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String ps = "";
            switch (c) {
                case 'A':
                    ps = "00";
                    break;
                case 'C':
                    ps = "01";
                    break;
                case 'G':
                    ps = "10";
                    break;
                case 'T':
                    ps = "11";
                    break;
            }
            sb.append(ps);
        }
        int start = 0;
        int end = 20;
        s = sb.toString();
        String sub = s.substring(start, end);
        int cur = parse(sub);
        rec[cur]++;
        while (end<s.length()-1) {
            cur&=262143;
            cur <<= 1;
            cur += s.charAt(end++)-'0';
            cur<<=1;
            cur += s.charAt(end++)-'0';
            rec[cur]++;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < rec.length; i++) {
            if (rec[i] > 1) {
                res.add(parse(i));
            }
        }
        return res;
    }

    private int parse(String s) {
        return Integer.parseInt(s, 2);
    }

    private String parse(int i) {
        StringBuilder s = new StringBuilder(Integer.toBinaryString(i));
        StringBuilder sb = new StringBuilder();

        while (s.length() < 20) {
            s.insert(0, "0");
        }
        int start = 0;
        while (start <= s.length() - 2) {
            String parseStr = "";
            switch (s.substring(start, start+2)){
                case "00":
                    parseStr = "A";
                    break;
                case "01":
                    parseStr = "C";
                    break;
                case "10":
                    parseStr = "G";
                    break;
                case "11":
                    parseStr = "T";
                    break;
            }
            sb.append(parseStr);
            start+=2;
        }
        return sb.toString();
    }
}

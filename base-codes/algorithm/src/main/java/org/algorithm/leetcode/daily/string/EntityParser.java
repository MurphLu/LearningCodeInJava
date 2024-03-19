package org.algorithm.leetcode.daily.string;

import java.util.HashMap;
import java.util.Map;

public class EntityParser {
    public static void main(String[] args) {
        System.out.println(entityParser("&&"));
    }
    public static String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
        int startIdx = 0;
        int idx = 1;
        StringBuilder sb = new StringBuilder();
        while (idx <= text.length()) {
            if (text.charAt(startIdx) == '&') {
                if (text.charAt(idx - 1) == '&') {
                    sb.append(text, startIdx, idx - 1);
                    startIdx = idx - 1;
                } else if (text.charAt(idx - 1) == ';') {
                    String s = text.substring(startIdx, idx);
                    sb.append(map.getOrDefault(s, s));
                    startIdx = idx;
                } else if (idx - startIdx > 6) {
                    sb.append(text, startIdx, idx);
                    startIdx = idx;
                }
                if (idx == text.length() && idx - startIdx == 1) {
                    sb.append(text, startIdx, idx);
                }
                idx++;
            } else {
                sb.append(text, startIdx, idx);
                idx++;
                startIdx++;
            }

        }
        System.out.println(startIdx+ "  " +idx);
        return sb.toString();
    }
}
